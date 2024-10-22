package com.example.mung.controller;

import com.example.mung.domain.LoginDTO;
import com.example.mung.domain.UserDTO;
import com.example.mung.domain.UserVO;
import com.example.mung.service.LoginService;
import com.example.mung.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
@AllArgsConstructor
public class LoginController {


    //user 정보
    @Autowired
    private UserService userService;
    //로그인한 사람의 정보 추출 경로
    //login 서비스
    @Autowired
    private LoginService loginService;

    //로그인 페이지로 이동하면서 idList 긁어오기
    @GetMapping("/login")
    public String loginView(Model model) {
        model.addAttribute("idList", loginService.idList());
        System.out.println("로그인 화면 출력");
        return "login";
    }

    //로그인 성공시 session에 로그인 정보 담기
    @PostMapping("/login")
    public String join(HttpSession session, LoginDTO dto) {
        UserVO user = loginService.loginSuccess(dto);

        if (user != null) {
            session.setAttribute("userInfo", user);  // 세션에 사용자 정보 저장
            System.out.println("로그인 성공");
            return "redirect:/mainPage";  // 메인 페이지로 리다이렉트
        } else {
            System.out.println("로그인 실패");
            return "redirect:/login";  // 로그인 실패 시 로그인 페이지로 리다이렉트
        }
    }


    //아이디 찾기 기능
    @PostMapping("/findId")
    @ResponseBody
    public ResponseEntity<String> findId(@RequestBody Map<String, String> requestData) {
        String name = requestData.get("name");
        String email = requestData.get("email");
        String birth = requestData.get("birth");

        String year = "";
        String month = "";
        String day = "";
        int count = Integer.parseInt(birth.substring(0, 2));
        if (count < 50) {

            year = "20" + birth.substring(0, 2); // "20" + "02"

        } else {
            year = "19" + birth.substring(0, 2);
        }
        month = birth.substring(2, 4); // "08"
        day = birth.substring(4, 6); // "19"

        String date = year + "-" + month + "-" + day;
        System.out.println(date);
        System.out.println(name + email + birth);
        LocalDate formatBirth = LocalDate.parse(date);
        System.out.println(formatBirth);
        LocalDateTime localDateTime = formatBirth.atStartOfDay(); // LocalDateTime으로 변환

        System.out.println(localDateTime);


        String foundId = loginService.findId(name, email, localDateTime);

        System.out.println(foundId);
        if (foundId != null) {
            return ResponseEntity.ok(foundId); // 찾은 아이디 반환
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User ID not found");
        }
    }

    //비밀번호 찾기 기능
    @PostMapping("/idCheckForModifyPassword")
    @ResponseBody
    public ResponseEntity<String> idCheckForModifyPassword(@RequestBody Map<String, String> requestData) {

        String id = requestData.get("id");
        String email = requestData.get("email");
        String birth = requestData.get("birth");

        String year = "";
        String month = "";
        String day = "";
        int count = Integer.parseInt(birth.substring(0, 2));
        if (count < 50) {

            year = "20" + birth.substring(0, 2); // "20" + "02"

        } else {
            year = "19" + birth.substring(0, 2);
        }
        month = birth.substring(2, 4); // "08"
        day = birth.substring(4, 6); // "19"

        String date = year + "-" + month + "-" + day;
        System.out.println(date);
        System.out.println(id + email + birth);

        // LocalDate로 변환
        LocalDate formatBirth = LocalDate.parse(date);
        System.out.println(formatBirth);
        LocalDateTime localDateTime = formatBirth.atStartOfDay(); // LocalDateTime으로 변환

        System.out.println(localDateTime);

        String foundName = loginService.idCheckForModifyPassword(id, email, localDateTime);

        System.out.println(foundName);
        if (foundName != null) {
            return ResponseEntity.ok(foundName + "님 의 \n비밀번호를 수정합니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("아이디를 찾지 못 했습니다.");
        }
    }

    //비밀번호 찾은 후 수정 기능
    @Transactional
    @PostMapping("/updatePassword")
    @ResponseBody
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> requestData) {
        String id = requestData.get("id");
        String newPassword = requestData.get("newPassword");

        int result;
        try {
            result = loginService.updatePassword(id, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("비밀번호 수정 중 오류 발생");
        }

        if (result > 0) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("아이디를 찾지 못 했습니다.");
        }
    }

    //일반 회원가입 기능
    @Transactional
    @PostMapping
    public String generalJoin(UserDTO dto) {
        System.out.println(dto);
        UserVO vo = new UserVO(dto.getUser_name(), dto.getUser_email(), dto.getPassword(), dto.getUser_phone(), dto.getUser_birth(), dto.getUser_gender(), dto.getNickname(), dto.getUser_loginId());
        boolean result = userService.register(vo);
        System.out.println(result);
        return "redirect:/login";
    }

//    //사업자 회원가입 기능
//    @Transactional
//    @PostMapping
//    public String businessJoin(UserDTO dto){
//        System.out.println(dto);
//        userService.register(vo);
//        return "redirect:/login";
//    }


}

