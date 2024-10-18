package com.example.mung.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccomDTO {
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
