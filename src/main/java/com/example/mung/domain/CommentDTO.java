package com.example.mung.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private int comment_id;
    private int post_id;
    private int user_id;
    private String content;
    private LocalDateTime created_at;
    private int parent_comment_id;

}
