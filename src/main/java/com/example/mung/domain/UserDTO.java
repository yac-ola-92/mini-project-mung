package com.example.mung.domain;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.HashMap;

@Getter
@ToString
public class UserDTO {
    private int user_id;
    private String user_loginId;
    private String user_name;
    private String user_email;
    private String user_phone;
    private LocalDateTime user_birth;
    private int user_gender; // 1,3: 남자 / 2,4: Female,
    private String nickname;
    private String role; //USER, HOST, ADMIN
    private String user_address;
    private String profile_image_url;
    private String pet_info; //JSON
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String business_number;
    private String business_sns_url;

    //하.. 반려견 정보 출력하는 메서드
    public String getPet_infoToString() {
        if (this.pet_info == null) {
            return "반려동물 정보가 없습니다.";
        }

        JSONObject jo = new JSONObject(pet_info);
        System.out.println("펫 정보 문자열 변환 출력");
        String name = jo.optString("이름", "정보 없음");
        String type = jo.optString("종", "정보 없음");
        String age = jo.optString("나이", "정보 없음");
        String weight = jo.optString("무게", "정보 없음");

        return String.format("이름: %s, 종: %s, 나이: %s, 무게: %s", name, type, age, weight);
    }

    //후아..반려견 정보를 Map으로 담아서 View에 던지기 좋게 만들기
    public HashMap<String, String> getPet_infoList() {
        if (pet_info == null) {
            return new HashMap<>();
        }
        JSONObject jo = new JSONObject(pet_info);
        System.out.println("펫 정보 리스트 변환 출력");
        HashMap<String, String> list = new HashMap<>();
        list.put("이름", jo.optString("이름"));
        list.put("종", jo.optString("종"));
        list.put("나이", jo.optString("나이"));
        list.put("무게", jo.optString("무게"));

        return list;
    }

    //문자열인 권한을 배열로 하나씩 담아서 보내기 어디든..
    public String[] getRole(){

        String [] roles = role.split(",");

        return roles;
    }


}
