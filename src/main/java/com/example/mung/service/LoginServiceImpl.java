/*
package com.example.mung.service;

import com.example.mung.domain.LoginDTO;
import com.example.mung.domain.UserDTO;
import com.example.mung.domain.UserVO;
import com.example.mung.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper mapper;

    @Override
    public UserVO loginSuccess(LoginDTO dto){
        return mapper.loginActive(dto);
    }

    @Override
    public boolean idCheck(String user_loginId){
        return mapper.loginByIdCheck(user_loginId);
    }

    @Override
    public String printName(String user_loginId){
        return mapper.nameCheck(user_loginId);
    }


//    public UserVO loginCheck(LoginDTO dto) {
//        UserVO user = mapper.findByUserLoginId(dto.getUser_loginId());
//        if (user == null || mapper.findByUserLoginId(dto.getUser_loginId()) != null) {
//            throw new RuntimeException("아이디와 비밀번호를 확인하세요.");
//        }
//        return user;
//    }
//
//    @Override
//    public UserVO findUserLoginId(String id){
//        System.out.println("Service단 : findByUserId실행");
//        return mapper.findLoginId(id);
//    }
//
//    @Override
//    public UserVO login(LoginDTO dto){
//        System.out.println("Service단 : login 실행");
//        return mapper.loginActive(dto);
//    }
//



//    @Override
//    public UserVO findUserLoginId(String id) {
//        return null;
//    }
//
//    @Override
//    public UserVO loginService(LoginDTO dto) {
//        return null;
//    }
}
*/
