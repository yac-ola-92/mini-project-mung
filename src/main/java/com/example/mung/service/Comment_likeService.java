package com.example.mung.service;

import com.example.mung.domain.Comment_likeDTO;

import java.util.List;

public interface Comment_likeService {
    // 좋아요/싫어요 추가 또는 업데이트
    boolean likeOrDislike(Comment_likeDTO commentLikeDTO);

    // 댓글의 좋아요 카운트 조회
    int getLikeCount(int comment_id);

    // 댓글의 싫어요 카운트 조회
    int getDislikeCount(int comment_id);
}
