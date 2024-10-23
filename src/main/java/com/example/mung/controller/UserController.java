package com.example.mung.controller;

import com.example.mung.domain.UserVO;
import com.example.mung.domain.transfer.PetInfo;
import com.example.mung.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/myPage")
    public String goMyPage(HttpSession session) {
        if (session.getAttribute("userInfo") == null) {
            return "/mainPage";
        } else {

            System.out.println("myPage 이동");
            return "/myPage";
        }
    }

    @GetMapping("/userInformation")
    public String goUserInformation(HttpSession session) {

        if (session.getAttribute("userInfo") == null) {
            return "/mainPage";
        } else {
            System.out.println("userInformation 이동");
            return "/userInformation";
        }

    }

    @Transactional
    @PostMapping("/updateUser")
    public String updateUser(HttpServletRequest request, HttpSession session) {

        UserVO info = (UserVO) session.getAttribute("userInfo");
        UserVO vo = new UserVO();
        request.getParameter("name");
        request.getParameter("type");
        request.getParameter("weight");
        request.getParameter("age");
        vo.setPet_inform(new PetInfo(request.getParameter("name"), request.getParameter("type"), request.getParameter("age"), request.getParameter("weight")));
        vo.setUser_id(info.getUser_id());
        System.out.println(vo);
        boolean result = service.modify_pet(vo);
        System.out.println(result);
        info.setPet_info(vo.getPet_info());
        session.setAttribute("userInfo",info);
        return "redirect:/myPage";
    }

}
