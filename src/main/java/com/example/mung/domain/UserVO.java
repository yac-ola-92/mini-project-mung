package com.example.mung.domain;

import com.example.mung.domain.transfer.PetInfo;
import com.example.mung.domain.transfer.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private int user_id;
    private String user_name;
    private String user_email;
    private String password;
    private String user_phone;
    private LocalDateTime user_birth;
    private int user_gender; // 1,3: 남자 / 2,4: Female,
    private String nickname;
    private String role; //USER, HOST, ADMIN
    private String profile_image_url;
    private String pet_info; //JSON
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String business_number;
    private String business_sns_url;
    private String user_loginId;

    //일반 회원가입
    public UserVO(String user_name,String user_email,String password,String user_phone,LocalDateTime user_birth,int user_gender,String nickname,String user_loginId){
        this.user_name =user_name;
        this.user_email = user_email;
        this.password = password;
        this.user_phone = user_phone;
        this.user_birth =user_birth;
        this.user_gender = user_gender;
        this.nickname = nickname;
        this.role = "USER";
        this.user_loginId =user_loginId;
    }

    //사업자 회원가입
    public UserVO(String user_name,String user_email,String password,String user_phone,LocalDateTime user_birth,int user_gender,String nickname,String user_loginId,String business_number){
        this.user_name =user_name;
        this.user_email = user_email;
        this.password = password;
        this.user_phone = user_phone;
        this.user_birth =user_birth;
        this.user_gender = user_gender;
        this.nickname = nickname;
        this.role = "USER,HOST";
        this.business_number = business_number;
        this.user_loginId =user_loginId;
    }

    public UserVO(String user_loginId,String password){
        this.user_loginId =user_loginId;
        this.password = password;
    }

    public void setUser_birthToString(String birth){
        this.user_birth = LocalDate.parse(birth).atStartOfDay();
    }

    public void setUser_gender(String gender) {
        int genderNum;
        int birth = this.user_birth.getYear();

        if (birth >= 2000) {
            if (gender.equals("남자")) {
                genderNum = 3;
            } else if(gender.equals("여자")){
                genderNum = 4; // "여자"인 경우
            }else {
                return;
            }
        } else { //2000년생 미만일 경우
            if (gender.equals("남자")) {
                genderNum = 1;
            } else if(gender.equals("여자")){
                genderNum = 2; // "여자"인 경우
            }else {
                return;
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
