package com.example.mung.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDTO {
    private int comment_id; // 댓글 ID
    private int post_id; // 게시물 ID
    private int user_id; // 작성자 ID

    @NonNull
    private String content; // 댓글 내용

    private final LocalDateTime created_at = LocalDateTime.now(); // 생성 시 현재 시간을 설정
    private int parent_comment_id = 0; // 부모 댓글 ID (없으면 0)
}
