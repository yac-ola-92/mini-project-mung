package com.example.mung.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@ToString
public class ReviewVO {
    private final int review_id;
    private final int user_id;
    private final String nickname;
    private final int rv_id;
    private final int rating;
    private final String comment;
    private final LocalDateTime created_at;
    private final String room_name;
    private final String accom_name;
    private String accom_images_url;
    private List<String> accom_images;

    public ReviewVO(int review_id, int user_id, String nickname, int rv_id,
                    int rating, String comment, LocalDateTime created_at, String room_name, String accom_name, String accom_images_url) {
        this.review_id = review_id;
        this.user_id = user_id;
        this.nickname = nickname;
        this.rv_id = rv_id;
        this.rating = rating;
        this.comment = comment;
        this.created_at = created_at;
        this.room_name = room_name;
        this.accom_name = accom_name;
        this.accom_images_url = accom_images_url;

        // 쉼표로 구분된 accom_images_url을 분리하여 리스트로 저장
        if (accom_images_url != null && !accom_images_url.isEmpty()) {
            this.accom_images = Arrays.asList(accom_images_url.split(","));
        }
    }
}
