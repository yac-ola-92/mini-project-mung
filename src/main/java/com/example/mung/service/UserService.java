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

    //일반회원 등록
    boolean register(UserVO vo);

    //사업자회원 등록
    boolean register_b(UserVO vo);

    //회원 수정
    boolean modify(UserVO vo);

    //회원 삭제
    boolean remove(int id);
}
