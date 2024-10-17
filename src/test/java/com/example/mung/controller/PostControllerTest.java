package com.example.mung.controller;

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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostControllerTest {

    //Spring MVC의 가상 MVC 환경 설정
    private MockMvc mockMvc;

    // PostService 클래스의 인스턴스를 모킹하여, 실제 구현 없이 테스트
    @Mock
    private PostService postService;

    // PostController에 postService를 주입. 모킹된 PostService 사용.
    @InjectMocks
    private PostController postController;

    //테스트 준비
    @BeforeEach
    public void setUp() {
        //Mockito 주석을 사용한 모킹 객체들을 초기화. 이를 통해 @Mock와 @InjectMocks 작동.
        MockitoAnnotations.openMocks(this);
        //postController를 사용하는 MockMvc 인스턴스를 생성. standaloneSetup은 단일 컨트롤러에 대한 설정 가능케 해줌.
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    // 전체 게시글 조회
    @Test
    public void testGetAllpost() throws Exception {
        // PostDTO 객체를 담을 빈 리스트 생성.
        // 이후 Mock 객체가 반환할 데이터 추가.
        List<PostDTO> post = new ArrayList<>();

        //postService.findAll() 호출 시, 위에서 만든 빈 리스트 post 반환하도록 설정.
        // 이렇게 함으로써 실제 서비스 구현없이 테스트 진행.
        when(postService.findAll()).thenReturn(post);

        //post 경로에 GET 요청을 보내고 응답 상태 확인.
        // perform() 메소드는 요청을 실행하며, andExpect()는 예상되는 결과를 검증.
        mockMvc.perform(get("/post"))
                .andExpect(status().isOk());
    }

    // 게시글 생성
    @Test
    public void testInsertPost() throws Exception {
        // PostDTO 객체를 생성, 생성할 게시글의 데이터 담기.
        PostDTO post = new PostDTO();
        post.setTitle("Test Title");
        post.setContent("Test Content");

        //postService.register() 메소드가 호출될 때, 항상 true를 반환하도록 설정
        when(postService.register(any(PostDTO.class))).thenReturn(true);

        //JSON 형식의 게시글 데이터를 포함한 POST 요청을 /post 경로로 보내기
        //요청 헤더에는 Contetn-Type을 application/json으로 설정하여 JSON데이터임을 명시
        mockMvc.perform(post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Title\", \"content\":\"Test Content\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePost() throws Exception {
        PostDTO post = new PostDTO();
        post.setPost_id(1);
        post.setTitle("Updated Title");
        post.setContent("Updated Content");

        when(postService.modify(any(PostDTO.class))).thenReturn(true);

        mockMvc.perform(put("/post/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Title\", \"content\":\"Updated Content\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePost() throws Exception {
        when(postService.remove(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/post/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPostByTitle() throws Exception {
        List<PostDTO> post = new ArrayList<>();
        post.add(new PostDTO());
        when(postService.findByCategory(any())).thenReturn(post);

        mockMvc.perform(get("/post/search")
                        .param("keyword", "Test Title")
                        .param("type", "title"))
                .andExpect(status().isOk());
    }
}
