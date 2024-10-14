package com.example.mung;


import com.example.mung.controller.PostController;
import com.example.mung.domain.PostDTO;
import com.example.mung.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerTest {
    private MockMvc mockMvc;
    /*MockMvc 인스턴스 변수 선언
    * HTTP요청을 시뮬레이션 하는데 사용됨. */

    @Mock
    private PostService postService; // Mock 객체로 사용할 PostService 변수 선언

    @InjectMocks
    private PostController postController; // Mock 객체를 주입받을 PostController 변수 선언

    @BeforeEach /*MockMvc 인스턴스를 초기화. 각 테스트 메소드 실행 전에 호출되는 메소드*/
    public void setUp() {
        MockitoAnnotations.openMocks(this); /*Mockito를 사용하여 Mock 객체를 생성*/
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
        /*MockMvc 인스턴스를 생성. postController를 테스트 할 때 사용할 수 있도록 설정.*/
    }

    @Test // 게시글 전체 조회
    public void testGetAllpost() throws Exception{
        when(postService.findAll()).thenReturn(List.of(new PostDTO(), new PostDTO()));

        mockMvc.perform(get("/post")) /*GET 요청을 "/post" URL로 보내고 */
                .andExpect(status().isOk())
                /* andExpect = assertThat과 유사하게, 특정 결과가 나타나도록 기대함.
                그렇지 않은경우 assertionError를 얻음.
                응답 상태가 200인지 확인 */
                .andExpect(model().attributeExists("post"))
                /*응답 모델에 post라는 속성이 존재하는지 확인.*/
                .andReturn(); /*결과 반환*/
    }

    @Test // 특정 게시글 조회
    public void testGetPostByTitle() throws Exception{
        when(postService.readByTitle("아주 좋아요!")).thenReturn(new PostDTO());

        mockMvc.perform(get("/post/아주 좋아요!")) /* "/post/1" URL로 GET 요청.*/
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("post"))
                .andReturn();
    }

    @Test
    public void testCreatePost() throws Exception {
        mockMvc.perform(post("/post")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        /* 요청의 Content-Type을 URL 인코딩으로 설정 */
                        .param("title", "댕댕이 간식 추천!")
                        /*폼 파라미터로 제목 설정*/
                        .param("content", "이거 한번 먹여보세용~~")
                        .param("category", "general"))
                .andExpect(status().is3xxRedirection()) // 응답 상태가 3xx 리디렉션인지 확인
                .andReturn();

        verify(postService, times(1)).register(any(PostDTO.class));
        // postService의 register 메소드가 한 번 호출되었는지 확인
    }

    @Test
    public void testUpdatePost() throws Exception {
        mockMvc.perform(put("/post/1") /* 아이디가 1인 게시물을 수정*/
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "오늘 날씨가 너무 좋아요~")
                        .param("content", "날씨도 좋은데 댕댕이 데리고 산책가봐요~")
                        .param("category", "general"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        verify(postService, times(1)).modify(any(PostDTO.class));
    }

    @Test
    public void testDeletePost() throws Exception {
        mockMvc.perform(delete("/post/1"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        verify(postService, times(1)).remove(1);
    }
}