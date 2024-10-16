package com.example.mung.service;

import com.example.mung.domain.Comment_likeDTO;
import com.example.mung.mapper.Comment_likeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Comment_likeServiceImpl implements Comment_likeService {
    private Comment_likeMapper comment_likeMapper;

    @Autowired
    public Comment_likeServiceImpl(Comment_likeMapper comment_likeMapper) {
        this.comment_likeMapper = comment_likeMapper;
    }

    @Override
    public List<Comment_likeDTO> findAll() {
        return comment_likeMapper.getAllLike();
    }

    @Override
    public List<Comment_likeDTO>  readByUserId(int user_id) {
        return comment_likeMapper.getLikeByUserId(user_id);
    }

    @Override
    public boolean register(Comment_likeDTO comment_likeDTO) {
        return comment_likeMapper.insert(comment_likeDTO);
    }

    @Override
    public boolean modify(Comment_likeDTO comment_likeDTO) {
        return comment_likeMapper.update(comment_likeDTO);
    }

    @Override
    public boolean remove(int comment_id, int user_id) {
        return comment_likeMapper.delete(comment_id,user_id);
    }
}
