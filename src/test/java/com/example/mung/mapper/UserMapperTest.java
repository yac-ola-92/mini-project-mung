package com.example.mung.mapper;

import com.example.mung.domain.UserDTO;
import com.example.mung.domain.UserVO;
import com.example.mung.domain.transfer.PetInfo;
import com.example.mung.domain.transfer.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper mapper;

    @Test
    public void getListTest(){
        //mapper.getList().stream().forEach(System.out :: println);
        List<UserDTO> list = mapper.getList();
        list.stream().map(UserDTO::getPet_infoToString).forEach(System.out::println);
        list.stream().map(UserDTO::getPet_infoList).forEach(System.out::println);
        list.stream().forEach(System.out :: println);
        System.out.println("getList 완료!!!");
    }

    @Test
    public  void getOneTest(){
        System.out.println(mapper.getOne(1));
        System.out.println("getOneTest 완료!!!");
    }
    //user_name,user_loginId, user_email, password, user_phone, user_birth, user_gender, nickname, role, user_address,
    // profile_image_url, pet_info, business_number, business_sns_url
    @Test
    public void insertTest(){
        UserVO vo = new UserVO();
        vo.setUser_name("김길동");
        vo.setUser_loginId("user5");
        vo.setUser_email("example1234@kmail.com");
        vo.setPassword("1234");
        vo.setUser_phone("010-0000-0000");
        vo.setUser_birth("1992-08-17");
        vo.setUser_gender("남자");
        vo.setNickname("빵빵이");
        String[] str = {"USER","HOST"};
        Role role= new Role(str);
        vo.setRole(role);
        vo.setUser_address("서울시 송파구 가락동");
        vo.setProfile_image_url("/bbangbbang.png");
        PetInfo petInfo = new PetInfo("뽀삐","샤모에드","2","15kg");
        vo.setPet_info(petInfo);
        int result = mapper.insert(vo);
        System.out.println("등록 완료 : "+result);
        System.out.println("insertTest 완료!!");
    }

    @Test
    public void updateTest(){
        UserVO vo = new UserVO();
        vo.setUser_id(1);
        vo.setUser_name("김옥지");
        //vo.setUser_loginId("user5");
        vo.setUser_email("example4444@kmail.com");
        vo.setPassword("12345");
        vo.setUser_phone("010-1234-1234");
        vo.setUser_birth("2002-08-19");
        vo.setUser_gender("여자");
        vo.setNickname("옥지");
        String[] str = {"USER"};
        Role role= new Role(str);
        vo.setRole(role);
        vo.setUser_address("서울시 송파구 문정동");
        vo.setProfile_image_url("/okji.png");
        PetInfo petInfo = new PetInfo("빵빵이","요크셔테리어","3","4kg");
        vo.setPet_info(petInfo);
        mapper.update(vo);
        System.out.println("update 완료!!!");
    }
}
