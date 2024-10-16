package com.example.mung.controller;

import com.example.mung.service.Comment_likeService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class Comment_likeControllerTest {
    private MockMvc mockMvc;

    @Mock
    private Comment_likeService comment_likeService;

    @InjectMocks
    private Comment_likeController comment_likeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(comment_likeController).build();
    }


}
