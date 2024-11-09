package com.example.mung.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private int comment_id; // 댓글 ID
    private int post_id; // 게시물 ID
    private int user_id; // 작성자 ID
    private String content; // 댓글 내용
    private LocalDateTime created_at;
    private String nickname;
    private int likeCount; // 좋아요 수
    private int dislikeCount; // 싫어요 수
}
