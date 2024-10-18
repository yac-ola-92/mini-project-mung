
package com.example.mung.controller;

import com.example.mung.domain.LoginDTO;
import com.example.mung.domain.UserVO;
import com.example.mung.service.LoginService;
import com.example.mung.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

//@Controller
//@AllArgsConstructor
//@RequestMapping("/member")
public class LoginController {


//    //user 정보
//    @Autowired
//    private UserService userService;
//    //로그인한 사람의 정보 추출 경로
//    //login 서비스
//    @Autowired
//    private LoginService loginService;
//
//
//    @GetMapping("/login")
//    public String loginView(Model model) {
//        model.addAttribute("user_loginId",userService.findAll());
//        System.out.println("로그인 화면 출력");
//        return "login";
//    }
//
//    @PostMapping("/userLogin")
//    public ResponseEntity<String> userLogin(@RequestBody LoginDTO dto) {
//        try {
//            // UserVO 객체가 null인지 확인
//            UserVO user = loginService.loginService(dto);
//
//            // 로그인 성공: user가 null이 아닌 경우
//            if (user != null) {
//
//                return new ResponseEntity<>("Login Success", HttpStatus.OK);
//            } else {
//                // 로그인 실패: user가 null인 경우
//                return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception e) {
//            // 예외 처리
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}

