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
    private Type type = Type.LIKE; // 기본값을 LIKE로 설정
    private int likeCount;         // 좋아요 개수
    private int dislikeCount;      // 싫어요 개수

    public enum Type {
        LIKE,
        DISLIKE
    }
}