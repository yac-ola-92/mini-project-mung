package com.example.mung.service;

import com.example.mung.domain.LoginDTO;
import com.example.mung.domain.UserVO;

public interface LoginService {

    //회원 아이디로 조회
    UserVO findUserLoginId(String id);

    //로그인 정보
    UserVO loginService(LoginDTO dto);
}
