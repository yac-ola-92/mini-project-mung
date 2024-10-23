package com.example.mung.service;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.RoomDTO;
import com.example.mung.domain.RoomVO;
import com.example.mung.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceimpl implements RoomService {
    @Autowired
   private RoomMapper roomDAO;

    @Override
    public List<RoomDTO>findAll(){
        System.out.println("모든 숙소의 객실 출력!!!!");
        return roomDAO.getList();
    }

    @Override // 한 숙소에서 가지고 있는 객실 모두 출력
    public List<RoomDTO>readByAccom_id(int accom_id){
        System.out.println(accom_id+"번 숙소의 객실 모두 출력!!");
        return roomDAO.getListByAccom_id(accom_id);
    }

    @Override //반려동물의 종으로 숙소 출력
    public List<RoomDTO>readByPet_kind(String pet_kind){
        System.out.println(pet_kind+"까지 예약가능한 숙소입니다");
        return roomDAO.getListByPet_kind(pet_kind);
    }

    public RoomDTO readUrl(int accom_id){
        System.out.println("url 가져올게요");
        return roomDAO.getUrl(accom_id);
    }

    @Override  //숙소의 객실 등록
    public boolean register(RoomVO vo){
        System.out.println(vo.getRoom_name()+"의 객실 등록 성공!!");
        return roomDAO.insert(vo);
    }

    @Override //등록된 객실 수정
    public boolean modify(RoomVO vo){
        System.out.println(vo.getRoom_name()+"의 객실이 수정되었습니다.");
        return roomDAO.update(vo);
    }

    @Override  // 객실 삭제
    public boolean remove(int room_id) {
        System.out.println(room_id+"의 객실이 삭제되었습니다.");
        return roomDAO.delete(room_id);
    }
}
