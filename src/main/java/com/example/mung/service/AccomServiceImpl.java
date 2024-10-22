package com.example.mung.service;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.AccomVO;
import com.example.mung.mapper.AccomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 스프링에서 빈으로 인식하기위한 어노테이션
@Service
public class AccomServiceImpl implements AccomService {
    // AccomServiceImpl는 DAO 메소드 호출 구현
    @Autowired
    private AccomMapper accomDAO;  //AccomMapper 받아서 accomDAO 객체 생성

    @Override
    public List<AccomDTO>findAll(){
        return accomDAO.getList();
    }

    @Override
    public List<AccomDTO>readByLocation(String location ,int guestCount){  // 위치에 따른 숙소 출력
        System.out.println(location+" 지역에 있는 숙소를 출력합니다");
        return accomDAO.getListByLocation(location, guestCount);
    }

    @Override
    public AccomDTO readByAccom_id(int accom_id){
        System.out.println(accom_id + "해당하는 숙소 정보를 불러옵니다.");
        return accomDAO.getOneByAccom_id(accom_id);
    }
    @Override
    public List<AccomDTO>readByRating(){
        System.out.println("별점 높은 숙소들이라구~");
        return accomDAO.getListByRating();
    }

    @Override
    public boolean register(AccomVO vo){ //숙소 등록
        List<AccomVO>checkAccom = accomDAO.getoneByUserAndAccom_name(vo);
        System.out.println(checkAccom.isEmpty());
        if(!checkAccom.isEmpty()) {
            System.out.println("이미 존재해서 등록 안할거~!");
            throw new IllegalArgumentException("이미 존재하는 숙소");
            // 유저아이디와 숙소명으로 찾아낸 숙소를 checkAccom에 넣고 그 값이 존재하면
            // 존재하는 숙소임을 확인!
        }else {
            System.out.println(" 숙소 등록 성공");
            return  accomDAO.insert(vo);
            //그렇지 않다면 숙소를 삽입하고 객체반환;
        }
    }

    @Transactional
    @Override
    public boolean modify(AccomVO vo){ //숙소 수정
        System.out.println("숙소 업데이트 성공");
        return  accomDAO.update(vo);
    }

    @Override
    public boolean remove(int accom_id){ //숙소 삭제
        System.out.println("숙소 삭제 성공 ");
        return accomDAO.delete(accom_id);
    }

    @Override
    public List<AccomDTO> findAll() {
        return List.of();
    }
}
