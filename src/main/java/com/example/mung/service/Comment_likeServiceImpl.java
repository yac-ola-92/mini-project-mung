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
    public Map<String, Integer> likeOrDislike(Comment_likeDTO commentLikeDTO) {
        Comment_likeDTO existing = commentLikeMapper.findByCommentIdAndUserId(commentLikeDTO.getComment_id(), commentLikeDTO.getUser_id());

        if (existing != null) {
            commentLikeMapper.updateLikeDislike(commentLikeDTO);
        } else {
            commentLikeMapper.insertLikeDislike(commentLikeDTO);
        }

        return getLikeAndDislikeCounts(commentLikeDTO.getComment_id());
    }

    @Override
    public Map<String, Integer> getLikeAndDislikeCounts(int comment_id) {
        int likeCount = commentLikeMapper.getLikeCount(comment_id);
        int dislikeCount = commentLikeMapper.getDislikeCount(comment_id);

        Map<String, Integer> response = new HashMap<>();
        response.put("likeCount", likeCount);
        response.put("dislikeCount", dislikeCount);

        return response;
    }
}
