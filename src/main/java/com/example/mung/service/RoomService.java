package com.example.mung.service;

import com.example.mung.domain.RoomDTO;
import com.example.mung.domain.RoomVO;

import java.util.List;

public interface RoomService {
    public List<RoomDTO>findAll();
    public List<RoomDTO>readByAccom_id(int accom_id);
    public List<RoomDTO>readByPet_kind(String pet_kind);
    public boolean register(RoomVO vo);
    public boolean modify(RoomVO vo);
    public boolean remove(int room_id);
}
