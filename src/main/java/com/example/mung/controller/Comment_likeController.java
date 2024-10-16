package com.example.mung.controller;

import com.example.mung.service.Comment_likeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like") /*json형식으로 응답*/
public class Comment_likeController {

    @Autowired
    private Comment_likeService comment_likeService;



}
