package com.example.mung.service;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.AccomVO;
import com.example.mung.mapper.AccomMapper;
import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 스프링에서 빈으로 인식하기위한 이노테이션
@Service
public class AccomServiceImpl implements AccomService {
    // AccomServiceImpl는 DAO 메소드 호출 구현
    @Autowired
    private AccomMapper accomDAO;  //AccomMapper 받아서 accomDAO 객체 생성

    @Override
    public List<AccomDTO>getList() {
        // 숙소 리스트 DAO클래스의 메소드 호출
        return accomDAO.getList(); //전체 숙소 출력
    }

    @Override
    public List<AccomDTO>getListByLocation(String location){
        System.out.println(location+" 지역에 있는 숙소를 출력합니다");
        return accomDAO.getListByLocation(location);  // 위치에 따른 숙소 출력
    }

    @Override
    public AccomVO insert(AccomVO vo){
        List<AccomVO>checkAccom = accomDAO.getListByUserAndAccom_name(vo);
        System.out.println(checkAccom.isEmpty());
       if(!checkAccom.isEmpty()) {
           System.out.println("이미 존재해서 등록 안할거~!");
           throw new IllegalArgumentException("이미 존재하는 숙소");
           // 유저아이디와 숙소명으로 찾아낸 숙소를 checkAccom에 넣고 그 값이 존재하면
           // 존재하는 숙소임을 확인!
       }else {
           System.out.println(" 숙소 등록 성공");
            accomDAO.insert(vo);
           //그렇지 않다면 숙소를 삽입하고 객체반환;
       }
        return vo;
    }

    @Override
    public AccomVO update(AccomVO vo){
        System.out.println("숙소 업데이트 성공");
        accomDAO.update(vo);
        return vo;
    }
}
