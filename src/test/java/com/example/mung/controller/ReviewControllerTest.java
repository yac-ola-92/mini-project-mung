//package com.example.mung.controller;
//
//import com.example.mung.domain.ReviewDTO;
//import com.example.mung.service.ReviewService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import java.util.List;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//public class ReviewControllerTest {
//    private MockMvc mockMvc;
//
//    @Mock
//    private ReviewService reviewService;
//
//    @InjectMocks
//    private ReviewController reviewController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
//    }
//
//    @Test
//    public void testGetAllReviews() throws Exception {
//        when(reviewService.findAll()).thenReturn(List.of(new ReviewDTO(), new ReviewDTO()));
//
//        mockMvc.perform(get("/review"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("review"))
//                .andReturn();
//    }
//
//    @Test
//    public void testGetReviewByRating() throws Exception {
//        when(reviewService.readByRating(5)).thenReturn(List.of(new ReviewDTO(), new ReviewDTO()));
//
//        mockMvc.perform(get("/review/5"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("review"))
//                .andReturn();
//    }
//
//    @Test
//    public void testCreateReview() throws Exception {
//        mockMvc.perform(post("/review")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("rating", "3")
//                .param("comment", "쏘쏘~~"))
//                .andExpect(status().is3xxRedirection()) // 응답 상태가 3xx 리디렉션인지 확인
//                .andReturn();
//
//        verify(reviewService, times(1)).register(any(ReviewDTO.class));
//    }
//
//    @Test
//    public void testUpdateReview() throws Exception {
//        mockMvc.perform(put("/review/3")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("rating", "2")
//                        .param("comment", "여러모로 아쉽네요.."))
//                .andExpect(status().is3xxRedirection())
//                .andReturn();
//
//        // 여기에서 modify 메소드를 검증해야 함
//        verify(reviewService, times(1)).modify(any(ReviewDTO.class));
//    }
//
//    @Test
//    public void testDeleteReview() throws Exception {
//        mockMvc.perform(delete("/review/3"))
//                .andExpect(status().is3xxRedirection())
//                .andReturn();
//
//        verify(reviewService, times(1)).remove(3);
//    }
//}
