package com.example.mung.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Comment_likeVO {
    private final int like_id;
    private final int comment_id;
    private final int user_id;
    private final Type type;

    public enum Type {
        LIKE, DISLIKE
    }
    public Comment_likeVO(int like_id, int comment_id, int user_id, Type type) {
        this.like_id = like_id;
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.type = type;
    }
}
