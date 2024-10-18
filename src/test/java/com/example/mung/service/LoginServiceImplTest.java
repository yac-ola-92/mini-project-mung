package com.example.mung.service;

import com.example.mung.domain.LoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginServiceImplTest {

    @Autowired
    private LoginService service;


    @Test
    public void loginSuccessTest(){
        System.out.println("ServiceImpl 실행");
        System.out.println(service.loginSuccess(new LoginDTO("dwi","123456")));
        System.out.println("loginSuccess 성공!!");
    }


    @Test
    public void idCheck(){
        System.out.println("ServiceImpl 실행");
        System.out.println(service.idCheck("dwi"));
        System.out.println("loginSuccess 성공!!");
    }

    @Test
    public void printName(){
        System.out.println("ServiceImpl 실행");
        System.out.println(service.printName("dwi"));
        System.out.println("loginSuccess 성공!!");
    }


//    @Test
//    public void findByUserLoginIdTest(){
//        System.out.println("Service Implements 실행 = findByUserLoginId");
//        System.out.println(service.findByUserLoginId("user"));
//    }
//
//    @Test
//    public void login(){
//        System.out.println("Service Implements 실행 = login");
//        System.out.println(service.login(new LoginDTO("user","12345")));
//    }
}
