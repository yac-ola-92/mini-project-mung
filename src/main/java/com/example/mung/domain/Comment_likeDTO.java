package com.example.mung.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment_likeDTO {
    private int like_id;
    private int comment_id;
    private int user_id;
    private Type type;
    public enum Type {
        LIKE, DISLIKE
    }
}
