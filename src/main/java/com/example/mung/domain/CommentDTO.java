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
    private int parent_comment_id = 0; // 부모 댓글 ID (없으면 0)

    private String nickname;  // 추가된 필드: 작성자의 닉네임
}
