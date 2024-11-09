package com.example.mung.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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
    private final String filePath; // 파일 경로 저장 시 사용
    private final byte[] fileData; // 파일 내용을 DB에 저장할 경우 사용

    public enum Category {
        rec, general, travel
    }

    public PostVO(int post_id, int user_id, String nickname, String title, String content, int view_count,
                  LocalDateTime created_at, LocalDateTime updated_at, Category category, String filePath, byte[] fileData) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.view_count = view_count;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.category = category;
        this.filePath = filePath;
        this.fileData = fileData;
    }
}

