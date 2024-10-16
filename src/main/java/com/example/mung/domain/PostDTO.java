package com.example.mung.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "제목은 비워 놓을 수 없습니다.")
    private String title;

    @NotNull(message = "내용은 비워 놓을 수 없습니다.")
    private String content;

    private int view_count;

    @NotNull(message = "카테고리를 선택해 주세요.")
    private Category category;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @NotNull(message = "비밀번호 4자리는 필수로 입력해 주세요.")
    @Size(min = 4, max = 4)
    private String password;

    private byte[] files;

    public enum Category {
        rec, general, travel
    }
}
