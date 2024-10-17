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

    public CommentVO(int commentId, int postId, int userId, String content, LocalDateTime createdAt, int parentCommentId) {
        comment_id = commentId;
        post_id = postId;
        user_id = userId;
        this.content = content;
        created_at = createdAt;
        parent_comment_id = parentCommentId;
    }
}
