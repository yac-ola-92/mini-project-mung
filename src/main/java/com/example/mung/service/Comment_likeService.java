package com.example.mung.service;

import com.example.mung.domain.Comment_likeDTO;
import java.util.Map;

public interface Comment_likeService {
    // 좋아요/싫어요 추가 또는 업데이트 후 최신 개수를 반환
    Map<String, Integer> likeOrDislike(Comment_likeDTO commentLikeDTO);

    // 댓글의 좋아요 및 싫어요 카운트 조회
    Map<String, Integer> getLikeAndDislikeCounts(int comment_id);
}
