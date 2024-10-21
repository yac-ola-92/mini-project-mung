package com.example.mung.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment_likeDTO {
    private int like_id;
    private int comment_id;
    private int user_id;

    private Type type = Type.LIKE; // 기본값을 LIKE로 설정

    public enum Type {
        LIKE,
        DISLIKE
    }
}
