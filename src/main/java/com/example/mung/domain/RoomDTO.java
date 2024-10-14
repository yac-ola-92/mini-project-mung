package com.example.mung.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RoomDTO {
    private int room_id;
    private String room_name;
    private String room_type;
    private int room_price;
    private String room_images_url;
    private String room_info;
    private int room_amount;
    private String pet_kind;


}
