package com.example.mung.domain;

import com.example.mung.domain.transfer.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO {
    private int user_id;
    private String user_name;
    private String user_email;
    private String user_phone;
    private LocalDateTime user_birth;
    private int user_gender; // 1,3: 남자 / 2,4: Female,
    private String nickname;
    private List<Role> role; //USER, HOST, ADMIN
    private String user_address;
    private String profile_image_url;
    private JSONObject pet_info; //JSON
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String business_number;
    private String business_sns_url;
}
