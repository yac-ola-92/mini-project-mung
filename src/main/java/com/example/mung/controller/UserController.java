package com.example.mung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/myPage")
    public String goMyPage(){
        System.out.println("myPage 이동");
        return "/myPage";
    }

    @GetMapping("/userInformation")
    public String goUserInformation(){
        System.out.println("userInformation 이동");
        return "/userInformation";
    }
}
