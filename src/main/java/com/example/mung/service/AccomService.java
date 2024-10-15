package com.example.mung.service;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.AccomVO;

import java.util.List;

public interface AccomService {
    //AccomService는 DAO를 호출하는 역할
    // -> 인터페이스에서 정의된 메서드를실제로 구현

    public List<AccomDTO>getList();
    public List<AccomDTO>getListByLocation(String location);
    public AccomVO insert(AccomVO vo);
    public AccomVO update(AccomVO vo);
    public AccomVO delete(AccomVO vo);
}
