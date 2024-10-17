package com.example.mung.service;

import com.example.mung.domain.LoginDTO;
import com.example.mung.domain.UserVO;

public interface LoginService {

    //아이디 비밀번호로 로그인 성공 하는지 여부
    UserVO loginSuccess(LoginDTO dto);

    //아이디 중복체크용 메서드
    boolean idCheck(String user_loginId);

    //해당 아이디 이름 출력
    String printName(String user_loginId);

    //회원 아이디로 조회
//    UserVO findUserLoginId(String id);
//
//    //로그인 정보
//    UserVO loginService(LoginDTO dto);
}
