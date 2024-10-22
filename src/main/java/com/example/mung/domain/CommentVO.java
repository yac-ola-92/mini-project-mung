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
    private int parent_comment_id;
    private String nickname;  // 사용자 닉네임 추가

    public CommentVO(int commentId, int postId, int userId, String content, LocalDateTime createdAt, int parentCommentId, String nickname) {
        this.comment_id = commentId;
        this.post_id = postId;
        this.user_id = userId;
        this.content = content;
        this.created_at = createdAt;
        this.parent_comment_id = parentCommentId;
        this.nickname = nickname;
    }
}
