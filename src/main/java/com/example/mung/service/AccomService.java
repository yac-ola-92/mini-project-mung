package com.example.mung.service;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.AccomVO;

import java.util.List;

public interface AccomService {
    //AccomService는 DAO를 호출하는 역할
    // -> 인터페이스에서 정의된 메서드를실제로 구현
    public List<AccomDTO>readByLocation(String location, int guestCount);
    public List<AccomDTO>readByAccom_id(int accom_id);
    public List<AccomDTO>readByRationg();
    public boolean  register(AccomVO vo);
    public boolean modify(AccomVO vo);
    public boolean remove(int accom_id);
}
