package com.example.mung.controller;

import com.example.mung.domain.PostDTO;
import com.example.mung.domain.UserDTO;
import com.example.mung.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void setUp() {
        // ViewResolver 설정
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController)
                .setViewResolvers(viewResolver)
                .build();
    }

    // 게시글 목록 조회 테스트
    @Test
    public void testGetPostsByCategory() throws Exception {
        List<PostDTO> postList = new ArrayList<>();
        PostDTO post = new PostDTO();
        post.setPost_id(1);
        post.setTitle("Test Post");
        postList.add(post);

        when(postService.getPostsByCategory("rec")).thenReturn(postList);

        mockMvc.perform(get("/category/rec"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("posts"))
                .andExpect(view().name("postMain"));
    }

    // 게시글 검색 테스트
    @Test
    public void testSearchPosts() throws Exception {
        List<PostDTO> searchResults = new ArrayList<>();
        PostDTO post = new PostDTO();
        post.setTitle("Test Post");
        searchResults.add(post);

        when(postService.searchPostsByTitle("Test")).thenReturn(searchResults);

        mockMvc.perform(get("/search").param("keyword", "Test"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("posts"))
                .andExpect(view().name("postMain"));
    }

    // 게시글 작성 폼 이동 테스트
    @Test
    public void testCreatePostForm() throws Exception {
        mockMvc.perform(get("/new").sessionAttr("userInfo", new UserDTO()))
                .andExpect(status().isOk())
                .andExpect(view().name("postWrite"));
    }

    // 게시글 등록 테스트
    @Test
    public void testCreatePost() throws Exception {
        when(postService.register(any(PostDTO.class))).thenReturn(true);

        mockMvc.perform(post("/new")
                        .param("title", "Test Title")
                        .param("content", "Test Content")
                        .param("files", "")
                        .sessionAttr("userInfo", new UserDTO()))
                .andExpect(status().isOk())
                .andExpect(view().name("postWrite"))
                .andExpect(model().attribute("msg", "게시글과 파일이 성공적으로 저장되었습니다."));
    }

    // 게시글 상세 조회 테스트
    @Test
    public void testGetPostById() throws Exception {
        PostDTO post = new PostDTO();
        post.setPost_id(1);
        post.setTitle("Test Title");
        post.setContent("Test Content");

        when(postService.readById(1)).thenReturn(post);

        mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("post"))
                .andExpect(view().name("postDetail"));
    }

    // 게시글 수정 페이지 이동 테스트
    @Test
    public void testUpdatePostPage() throws Exception {
        PostDTO post = new PostDTO();
        post.setPost_id(1);
        post.setUser_id(1);
        post.setTitle("Test Title");

        UserDTO userInfo = new UserDTO();
        userInfo.getUser_id();

        when(postService.readById(1)).thenReturn(post);

        mockMvc.perform(get("/update/1")
                        .sessionAttr("userInfo", userInfo))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("post"))
                .andExpect(view().name("postUpdate"));
    }

    // 게시글 삭제 테스트
    @Test
    public void testDeletePost() throws Exception {
        PostDTO post = new PostDTO();
        post.setPost_id(1);
        post.setUser_id(1);

        UserDTO userInfo = new UserDTO();
        userInfo.getUser_id();

        when(postService.readById(1)).thenReturn(post);
        when(postService.remove(1)).thenReturn(true);

        mockMvc.perform(post("/delete/1")
                        .sessionAttr("userInfo", userInfo))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/postMain"));
    }
}
