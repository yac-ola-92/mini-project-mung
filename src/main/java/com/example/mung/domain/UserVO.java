package com.example.mung.domain;

import com.example.mung.domain.transfer.PetInfo;
import com.example.mung.domain.transfer.Role;
import lombok.*;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    private List<String> roles; // ROLE을 List로 변경

    // 일반 회원가입
    public UserVO(String user_name, String user_email, String password, String user_phone, LocalDateTime user_birth, int user_gender, String nickname, String user_loginId) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
        this.user_phone = user_phone;
        this.user_birth = user_birth;
        this.user_gender = user_gender;
        this.nickname = nickname;
        this.role = "USER"; // 기본 역할 설정
        this.user_loginId = user_loginId;
        this.roles = Arrays.asList(this.role); // 초기화
    }

    // 사업자 회원가입
    public UserVO(String user_name, String user_email, String password, String user_phone, LocalDateTime user_birth, int user_gender, String nickname, String user_loginId, String business_number) {
        this(user_name, user_email, password, user_phone, user_birth, user_gender, nickname, user_loginId);
        this.role = "USER,HOST"; // 사업자 역할 추가
        this.roles = Arrays.asList(this.role.split(",")); // 역할을 List로 변환
        this.business_number = business_number;
    }

    public UserVO(String user_loginId, String password) {
        this.user_loginId = user_loginId;
        this.password = password;
    }

    // 추가적인 역할 배열 설정
    public void setRole(String role) {
        this.role = role;
        this.roles = Arrays.asList(role.split(",")); // 역할을 List로 변환
    }

    public void setUser_birthToString(String birth) {
        this.user_birth = LocalDate.parse(birth).atStartOfDay();
    }

    public void setUser_gender(String gender) {
        if (gender == null) return; // null 체크 추가
        int genderNum;
        int birth = this.user_birth.getYear();

        if (birth >= 2000) {
            genderNum = gender.equals("남자") ? 3 : 4; // 삼항 연산자로 간단히
        } else { // 2000년생 미만일 경우
            genderNum = gender.equals("남자") ? 1 : 2; // 삼항 연산자로 간단히
        }

        this.user_gender = genderNum;
    }

    public void setPet_inform(PetInfo petInfo) {
        if (petInfo == null) return; // null 체크 추가
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("이름", petInfo.getName());
        jsonObject.put("종", petInfo.getType());
        jsonObject.put("나이", petInfo.getAge());
        jsonObject.put("무게", petInfo.getWeight());

        System.out.println("펫정보 입력 완료!!!");
        this.pet_info = jsonObject.toString();
    }

    // 배열로 넘어오는 role값을 (,) 콤마 기준으로 String으로 변환하여 보내기
    public void setRole(Role role) {
        if (role == null || role.getRole_arr() == null) {
            this.role = "";
            this.roles = Arrays.asList(); // 빈 리스트로 초기화
            return;
        }

        System.out.println("권한 정보 입력 완료!!");
        this.role = String.join(",", role.getRole_arr());
        this.roles = Arrays.asList(role.getRole_arr()); // 역할을 List로 변환
        System.out.println("롤 세터 작동"+this.role);
        System.out.println("롤 세터 작동"+this.roles);
    }

    public boolean hasRole(String roleToCheck) {
        if (this.roles == null) return false;
        return this.roles.contains(roleToCheck); // List의 contains 메서드 사용
    }

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
        JSONObject json = new JSONObject(pet_info);
        System.out.println("펫 정보 리스트 변환 출력");
        HashMap<String, String> list = new HashMap<>();
        list.put("이름", json.optString("이름"));
        list.put("종", json.optString("종"));
        list.put("나이", json.optString("나이"));
        list.put("무게", json.optString("무게"));

        return list;
    }
}
