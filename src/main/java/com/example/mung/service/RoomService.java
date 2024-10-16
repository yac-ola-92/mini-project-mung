package com.example.mung.service;

import com.example.mung.domain.RoomDTO;
import com.example.mung.domain.RoomVO;

import java.util.List;

public interface RoomService {
    public List<RoomDTO>getList();
    public List<RoomDTO>getListByAccom_id(int accom_id);
    public List<RoomDTO>getListByPet_kind(String pet_kind);
    public boolean insert(RoomVO vo);
    public boolean update(RoomVO vo);
    public boolean delete(int room_id);
}
