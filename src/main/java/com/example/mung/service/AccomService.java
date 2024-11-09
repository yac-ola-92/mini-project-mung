package com.example.mung.service;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.AccomVO;

import java.util.List;

public interface AccomService {
    //AccomService는 DAO를 호출하는 역할
    // -> 인터페이스에서 정의된 메서드를실제로 구현
    List<AccomDTO>findAll();
    List<AccomDTO>readByLocation(String location, int capacity);

    AccomDTO readByUser(int accom_id);
    List<AccomDTO> readByReview(int accom_id);

    List<AccomDTO>readByRating();
    AccomDTO readByAccomId(int accom_id);
    boolean  register(AccomVO vo);
    boolean modify(AccomVO vo);
    boolean remove(int accom_id);


}
