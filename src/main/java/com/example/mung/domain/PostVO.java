package com.example.mung.domain;

import lombok.Getter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@ToString
public class PostVO {
    private final int post_id;
    private final int user_id;
    private final String title;
    private final String content;
    private final int view_count;
    private final LocalDateTime created_at;
    private final LocalDateTime updated_at;
    private final Category category;

    public enum Category {
        rec, general, travel
    }

    public PostVO(int post_id, int user_id, String title, String content, int view_count,
                  LocalDateTime created_at, LocalDateTime updated_at, Category category) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.view_count = view_count;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.category = category;
    }
}
