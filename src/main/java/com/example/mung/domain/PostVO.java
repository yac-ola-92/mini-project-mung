package com.example.mung.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@ToString
public class PostVO {

    private final int post_id;
    private final int user_id;
    private final String nickname;
    private final String title;
    private final String content;
    private final int view_count;
    private final LocalDateTime created_at;
    private final LocalDateTime updated_at;
    private final Category category;
    private final String files; // 한 개의 파일로 수정

    public enum Category {
        rec, general, travel
    }

    public PostVO(int post_id, int user_id, String nickname, String title, String content, int view_count,
                  LocalDateTime created_at, LocalDateTime updated_at, Category category, String files) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.view_count = view_count;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.category = category;
        this.files = files; // 한 개의 파일 처리
    }
}
