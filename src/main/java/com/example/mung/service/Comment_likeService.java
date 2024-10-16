package com.example.mung.service;

import com.example.mung.domain.Comment_likeDTO;

import java.util.List;

public interface Comment_likeService {
    List<Comment_likeDTO> findAll();
    List<Comment_likeDTO> readByUserId(int user_id);
    boolean register(Comment_likeDTO comment_likeDTO);
    boolean modify(Comment_likeDTO comment_likeDTO);
    boolean remove(int comment_id, int user_id);
}
