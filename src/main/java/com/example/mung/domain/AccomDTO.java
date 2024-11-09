package com.example.mung.domain;



import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
    private String nickname;
    private String Comment;

    public List<String> getAccomImagesUrl(){
        List ur = Arrays.asList(getAccom_images_url().split(","));
        System.out.println("숙소 이미지 출력 : "+ ur);
        return ur;
    }
    public List<String> getAccomAmenities(){
        List amen = Arrays.asList(getAccom_amenities().split(","));
        System.out.println("숙소 편의시설 출력 : "+ amen);
        return amen;
    }



}
