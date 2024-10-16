package com.example.mung.controller;

import com.example.mung.domain.CommentDTO;
import com.example.mung.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CommentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    public void testGetAllComments() throws Exception {
        CommentDTO comment1 = new CommentDTO();
        CommentDTO comment2 = new CommentDTO();
        when(commentService.findAll()).thenReturn(List.of(comment1, comment2));

        mockMvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("comments"))
                .andReturn();
    }
    @Test
    public void testGetCommentsByUserId() throws Exception {
        int user_id = 1;
        CommentDTO comment1 = new CommentDTO();
        CommentDTO comment2 = new CommentDTO();
        when(commentService.readByUserId(user_id)).thenReturn(List.of(comment1, comment2));

        mockMvc.perform(get("/comments/user/{user_id}", user_id)) // URL 수정
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("comments"))
                .andExpect(model().attribute("comments", hasSize(2))); // 두 개의 댓글이 있는지 확인
    }

    @Test
    public void testGetCommentsByPostId() throws Exception {
        int post_id = 1;
        CommentDTO comment1 = new CommentDTO();
        CommentDTO comment2 = new CommentDTO();
        when(commentService.readByPostId(post_id)).thenReturn(List.of(comment1, comment2));

        mockMvc.perform(get("/comments/post/{post_id}", post_id)) // URL 수정
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("comments"))
                .andExpect(model().attribute("comments", hasSize(2))); // 두 개의 댓글이 있는지 확인
    }

    @Test
    public void testCreateComment() throws Exception {
        MvcResult result = mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("content", "Nice post!")
                        .param("post_id", "1")
                        .param("user_id", "1"))
                .andExpect(status().is3xxRedirection()) // 리디렉션 상태 확인
                .andReturn();

        verify(commentService, times(1)).register(any(CommentDTO.class)); // register 메서드 호출 확인
    }

    @Test
    public void testUpdateComment() throws Exception {
        int comment_id = 4;
        int post_id = 1;

        mockMvc.perform(put("/comments/{comment_id}", comment_id, post_id)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("content", "Updated comment!"))
                .andExpect(status().is3xxRedirection()); // 리디렉션 상태 확인

        verify(commentService, times(1)).modify(any(CommentDTO.class)); // modify 메서드 호출 확인
    }

    @Test
    public void testDeleteComment() throws Exception {
        int comment_id = 20;
        when(commentService.remove(comment_id)).thenReturn(true);

        mockMvc.perform(delete("/comments/{comment_id}", comment_id))
                .andExpect(status().is3xxRedirection());

        verify(commentService, times(1)).remove(comment_id);
    }

}
