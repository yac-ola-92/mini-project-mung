package com.example.mung.service;

import com.example.mung.domain.CommentDTO;
import java.util.List;

public interface CommentService {
    List<CommentDTO> findAll();
    List<CommentDTO> readByUserId(int user_id);
    List<CommentDTO> readByPostId(int post_id);
    boolean register(CommentDTO comment);
    boolean modify(CommentDTO comment);
    boolean remove(int comment_id);
    CommentDTO findById(int comment_id);
}
