package com.example.mung.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PostDTO {
    private int post_id;
    private int user_id;
    private String title;
    private String content;
    private int view_count;
    private Category category;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String password;
    private byte[] files;

    public enum Category {
        rec, general, travel
    }


}
