package com.example.mung.service;

import com.example.mung.domain.LoginDTO;
import com.example.mung.domain.UserDTO;
import com.example.mung.domain.UserVO;
import com.example.mung.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserDTO> findAll() {
        System.out.println("Service단 : findAll 실행");
        return mapper.getList();
    }

    @Override
    public UserDTO read(int id) {
        System.out.println("Service단 : read 실행");
        return mapper.getOne(id);
    }

    @Override
    public boolean register(UserVO vo) {
        System.out.println("Service단 : register 실행");
        return mapper.insert(vo);
    }

    @Override
    public boolean modify(UserVO vo) {
        System.out.println("Service단 : modify 실행");
        return mapper.update(vo);
    }

    @Override
    public boolean remove(int id) {
        System.out.println("Service단 : remove 실행");
        return mapper.delete(id);
    }
}
