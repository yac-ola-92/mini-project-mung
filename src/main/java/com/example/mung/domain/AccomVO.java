package com.example.mung.domain;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AccomVO {
    private int accom_id;
    private int user_id;
    private String accom_name;
    private String accom_location;
    private String accom_phone;
    private String accom_caution;
    private String accom_description;
    private String accom_images_url;
    private String accom_amenities;

}
