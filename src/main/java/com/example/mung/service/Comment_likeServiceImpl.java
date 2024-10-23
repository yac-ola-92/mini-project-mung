package com.example.mung.service;

import com.example.mung.domain.Comment_likeDTO;
import com.example.mung.mapper.Comment_likeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class Comment_likeServiceImpl implements Comment_likeService {

    private final Comment_likeMapper commentLikeMapper;

    @Autowired
    public Comment_likeServiceImpl(Comment_likeMapper commentLikeMapper) {
        this.commentLikeMapper = commentLikeMapper;
    }

    @Override
    @Transactional
    public boolean likeOrDislike(Comment_likeDTO commentLikeDTO) {
        // 이미 사용자가 좋아요 또는 싫어요를 눌렀는지 확인
        Comment_likeDTO existing = commentLikeMapper.findByCommentIdAndUserId(commentLikeDTO.getComment_id(), commentLikeDTO.getUser_id());

        if (existing != null) {
            // 이미 존재하면 타입을 업데이트
            return commentLikeMapper.updateLikeDislike(commentLikeDTO) > 0;
        } else {
            // 처음 누른 경우 삽입
            return commentLikeMapper.insertLikeDislike(commentLikeDTO) > 0;
        }
    }

    @Override
    public int getLikeCount(int comment_id) {
        return commentLikeMapper.getLikeCount(comment_id);
    }

    @Override
    public int getDislikeCount(int comment_id) {
        return commentLikeMapper.getDislikeCount(comment_id);
    }
}
