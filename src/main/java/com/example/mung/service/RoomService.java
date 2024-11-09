package com.example.mung.service;

import com.example.mung.domain.RoomDTO;
import com.example.mung.domain.RoomVO;

import java.util.List;

public interface RoomService {
    List<RoomDTO>findAll();
    List<RoomDTO>readByAccom_id(int accom_id);
    List<RoomDTO>readByPet_kind(String pet_kind);
    RoomDTO readOne(int room_id);
    RoomDTO readUrl(int accom_id);
    boolean register(RoomVO vo);
    boolean modify(RoomVO vo);
    boolean remove(int room_id);
}

