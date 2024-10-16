package com.example.mung.service;

import com.example.mung.domain.LoginDTO;
import com.example.mung.domain.UserDTO;
import com.example.mung.domain.UserVO;

import java.util.List;

public interface UserService {

    //회원 전체 조회
    List<UserDTO> findAll();

    //회원 번호로 조회
    UserDTO read(int id);

    //회원 등록
    int register(UserVO vo);

    //회원 수정
    int modify(UserVO vo);

    //회원 삭제
    int remove(int id);
}
