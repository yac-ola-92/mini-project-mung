package com.example.mung.service;

import com.example.mung.domain.LoginDTO;
import com.example.mung.domain.UserVO;
import com.example.mung.domain.transfer.PetInfo;
import com.example.mung.domain.transfer.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService service;

    @Test
    public void findAllTest() {
        System.out.println("Service Implements 실행 = findAll");
        service.findAll().stream().forEach(System.out::println);
    }

    @Test
    public void readTest() {
        System.out.println("Service Implements 실행 = read");
        System.out.println(service.read(1).toString());
    }


    @Test
    public void registerTest() {
        System.out.println("Service Implements 실행 = register");
        UserVO vo = new UserVO();
        vo.setUser_name("드와이트");
        vo.setUser_loginId("dwight111");
        vo.setPassword("123456");
        vo.setUser_birthToString("2002-12-12");
        vo.setUser_gender("남자");
        vo.setUser_email("dwight70@dundermifflin.net");
        vo.setNickname("manager");
        vo.setRole(new Role(new String[]{"USER,HOST"}));
        vo.setUser_phone("010-0000-1234");
        vo.setPet_inform(new PetInfo("", "인간", "100", "80kg"));
        boolean result = service.register(vo);
        System.out.println("서비스단 등록 성공 : " + result);
    }

    @Test
    public void modifyTest() {
        System.out.println("Service Implements 실행 = modify");
        UserVO vo = new UserVO();
        vo.setUser_id(16);
        vo.setUser_loginId("dwight22");
        vo.setUser_name("드와이트");
        vo.setUser_birthToString("1970-12-12");
        vo.setPassword("123456");
        vo.setUser_gender("남자");
        vo.setUser_email("dwight70@dundermifflin.net");
        vo.setNickname("manager");
        vo.setRole(new Role(new String[]{"HOST,ADMIN"}));
        vo.setUser_phone("010-0000-1232");
        vo.setPet_inform(new PetInfo("안젤라", "인간", "101", "81kg"));
        boolean result = service.modify(vo);
        System.out.println("서비스단 수정 성공 : " + result);

    }

    @Test
    public void removeTest() {
        System.out.println("Service Implements 실행 = remove");
        boolean result = service.remove(7);
        System.out.println("서비스단 삭제 성공 : " + result);
    }

}
