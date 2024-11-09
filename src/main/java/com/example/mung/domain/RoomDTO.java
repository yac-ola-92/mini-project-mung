package com.example.mung.domain;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDTO {
    private int room_id;
    private int accom_id;
    private String room_name;
    private String room_type;
    private int room_price;
    private String room_images_url;
    private String room_info;
    private int room_amount;
    private String pet_kind;
    private int capacity_standard;
    private int capacity_max;


    public List<String> getRoomImagesUrl() {
        return Arrays.asList(getRoom_images_url().split(","));
    }

}
