package com.example.mung.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class CommentVO {
    private final int comment_id;
    private final int post_id;
    private final int user_id;
    private final String content;
    private final LocalDateTime created_at;
    private final String nickname;

    private final int likeCount;     // 좋아요 수
    private final int dislikeCount;  // 싫어요 수


    public CommentVO(int commentId, int postId, int userId, String content, LocalDateTime createdAt, String nickname, int likeCount, int dislikeCount) {
        this.comment_id = commentId;
        this.post_id = postId;
        this.user_id = userId;
        this.content = content;
        this.created_at = createdAt;
        this.nickname = nickname;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }
}
