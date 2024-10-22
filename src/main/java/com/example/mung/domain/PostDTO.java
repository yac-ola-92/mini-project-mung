package com.example.mung.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class PostDTO {

    private int post_id;
    private int user_id;
    // 작성자 닉네임 필드 추가
    private String nickname;

    @NotNull(message = "제목은 비워 놓을 수 없습니다.")
    private String title;

    @NotNull(message = "내용은 비워 놓을 수 없습니다.")
    private String content;

    private int view_count;

    @NotNull(message = "카테고리를 선택해 주세요.")
    private Category category;

    private LocalDateTime created_at = LocalDateTime.now(); // 게시글 생성 시 현재 시간 자동 설정
    private LocalDateTime updated_at;

    @NotNull(message = "비밀번호 4자리는 필수로 입력해 주세요.")
    @Size(min = 4, max = 4, message = "비밀번호는 4자리여야 합니다.")
    @Pattern(regexp = "\\d{4}", message = "비밀번호는 숫자 4자리여야 합니다.") // 숫자만 허용
    private String password;

    private byte[] files;

    private List<CommentDTO> comments; // 게시글에 대한 댓글 리스트

    public enum Category {
        rec, general, travel
    }
}
