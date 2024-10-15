package com.example.mung.domain;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    public AccomVO(String accom_name,String accom_location, String accom_phone, String accom_caution,String accom_description,String accom_images_url,String accom_amenities){
        this.accom_name=accom_name;
        this.accom_location=accom_location;
        this.accom_phone=accom_phone;
        this.accom_caution=accom_caution;
        this.accom_description=accom_description;
        this.accom_images_url=accom_images_url;
        this.accom_amenities=accom_amenities;
    }
}
