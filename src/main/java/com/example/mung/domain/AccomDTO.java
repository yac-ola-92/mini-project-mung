package com.example.mung.domain;



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

    private int rating;
    private String pet_kind;
    private int room_price;
    private int capacity_standard;
    private int capacity_max;
    private String business_number;
    private String business_sns_url;
    private String comment;



}
