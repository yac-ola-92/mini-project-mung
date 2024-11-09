//package com.example.mung.controller;
//
//import com.example.mung.service.Comment_likeService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//public class Comment_likeControllerTest {
//    private MockMvc mockMvc;
//
//    @Mock
//    private Comment_likeService comment_likeService;
//
//    @InjectMocks
//    private Comment_likeController comment_likeController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(comment_likeController).build();
//    }
//
//    @Test
//    void testLikeComment() throws Exception {
//        int comment_id = 1;
//        int user_id = 1; // 하드코딩된 유저 ID
//
//        mockMvc.perform(post("/like/like/{comment_id}", comment_id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("user_id", String.valueOf(user_id)))  // 테스트에서 user_id를 전달
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(print());
//
//        Mockito.verify(comment_likeService).register(Mockito.any());
//    }
//
//    @Test
//    void testUnlikeComment() throws Exception {
//        int comment_id = 1;
//        int user_id = 1; // 하드코딩된 유저 ID
//
//        mockMvc.perform(delete("/like/like/{comment_id}", comment_id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("user_id", String.valueOf(user_id)))  // 테스트에서 user_id를 전달
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(print());
//
//        Mockito.verify(comment_likeService).remove(comment_id, user_id);
//    }
//}
