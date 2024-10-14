package com.example.mung.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReviewDTO {
    private int review_id;
    private int user_id;
    private int rv_id;
    private int rating;
    private String comment;
    private LocalDateTime created_at;
}
