package com.example.mung.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class PostDTO {

    private int post_id;
    private int user_id;
    private String nickname;
    @NotNull(message = "제목은 비워 놓을 수 없습니다.")
    private String title;
    @NotNull(message = "내용은 비워 놓을 수 없습니다.")
    private String content;
    private int view_count;
    @NotNull(message = "카테고리를 선택해 주세요.")
    private Category category;
    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at;
    @NotNull(message = "비밀번호 4자리는 필수로 입력해 주세요.")
    @Size(min = 4, max = 4, message = "비밀번호는 4자리여야 합니다.")
    @Pattern(regexp = "\\d{4}", message = "비밀번호는 숫자 4자리여야 합니다.")
    private String password;
    private byte[] files; // 파일 데이터를 담을 필드
    private String fileType;
    private List<CommentDTO> comments;

    public enum Category {
        rec, general, travel
    }
}

