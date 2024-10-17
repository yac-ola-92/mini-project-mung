package com.example.mung.service;

import com.example.mung.domain.CommentDTO;
import com.example.mung.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDTO> findAll() {
        return commentMapper.getList();
    }

    @Override
    public List<CommentDTO> readByUserId(int user_id) {
        return commentMapper.getListByUserId(user_id);
    }

    @Override
    public List<CommentDTO> readByPostId(int post_id) {
        return commentMapper.getListByPostId(post_id);
    }

    @Override
    public boolean register(CommentDTO comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public boolean modify(CommentDTO comment) {
        return commentMapper.update(comment);
    }

    @Override
    public boolean remove(int comment_id) {
        return commentMapper.delete(comment_id);
    }

}
