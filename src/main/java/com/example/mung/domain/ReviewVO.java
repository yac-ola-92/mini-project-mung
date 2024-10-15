package com.example.mung.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class ReviewVO {
    private final int review_id;
    private final int user_id;
    private final int rv_id;
    private final int rating;
    private final String comment;
    private final LocalDateTime created_at;

    public ReviewVO(int review_id, int user_id, int rv_id,
                    int rating, String comment, LocalDateTime created_at) {
        this.review_id = review_id;
        this.user_id = user_id;
        this.rv_id = rv_id;
        this.rating = rating;
        this.comment = comment;
        this.created_at = created_at;
    }

}
