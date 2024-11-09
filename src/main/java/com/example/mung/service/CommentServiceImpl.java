package com.example.mung.service;

import com.example.mung.domain.CommentDTO;
import com.example.mung.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDTO> findAll() {
        return commentMapper.getAllComment();
    }

    @Override
    public List<CommentDTO> readByUserId(int user_id) {
        return commentMapper.getCommentByUserId(user_id);
    }

    @Override
    public List<CommentDTO> readByPostId(int post_id) {
        return commentMapper.getCommentsByPostId(post_id);
    }

    @Override
    public boolean register(CommentDTO comment) {
        return commentMapper.insertComment(comment) > 0;
    }

    @Override
    public boolean modify(CommentDTO comment) {
        return commentMapper.updateComment(comment) > 0;
    }

    @Transactional
    @Override
    public boolean remove(int comment_id) {
        return commentMapper.deleteComment(comment_id) > 0;
    }

    @Override
    public CommentDTO findById(int comment_id) {
        return commentMapper.findById(comment_id);
    }
}
