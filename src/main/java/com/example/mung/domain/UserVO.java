package com.example.mung.domain;

import com.example.mung.domain.transfer.PetInfo;
import com.example.mung.domain.transfer.Role;
import lombok.Data;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserVO {
    private int user_id;
    private String user_loginId;
    private String user_name;
    private String user_email;
    private String password;
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

    public void setUser_birth(String birth){
        this.user_birth = LocalDate.parse(birth).atStartOfDay();
    }

    public void setUser_gender(String gender) {
        int genderNum;
        int birth = this.user_birth.getYear();

        if (birth >= 2000) {
            if (gender.equals("남자")) {
                genderNum = 3;
            } else {
                genderNum = 4; // "여자"인 경우
            }
        } else { //2000년생 미만일 경우
            if (gender.equals("남자")) {
                genderNum = 1;
            } else {
                genderNum = 2; // "여자"인 경우
            }
        }

        this.user_gender = genderNum;
    }


    public void setPet_info(PetInfo petInfo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("이름", petInfo.getName());
        jsonObject.put("종", petInfo.getType());
        jsonObject.put("나이", petInfo.getAge());
        jsonObject.put("무게", petInfo.getWeight());

        System.out.println("펫정보 입력 완료!!!");
        this.pet_info =jsonObject.toString();
    }

    //배열로 넘어오는 role값을 (,) 콤마 기준으로 String으로 변환하여 보내기
    public void setRole(Role role){

        if (role == null || role.getRole_arr() == null) {
            this.role = "";
            return;
        }

        System.out.println("권한 정보 입력 완료!!");

        this.role=String.join(",",role.getRole_arr());
    }

}
