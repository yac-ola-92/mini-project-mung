package com.example.mung;

import com.example.mung.domain.AccomVO;
import com.example.mung.mapper.AccomMapper;
import com.example.mung.service.AccomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccomServiceImplTest  {
    @Autowired
    private AccomService service;

    @Test
    public void testGetList() {
        service.getList().stream().forEach(System.out::println);
    }

    @Test
    public void testGetListByLocation() {
        service.getListByLocation("강원").stream().forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        AccomVO vo = new AccomVO();
        vo.setUser_id(1);
        vo.setAccom_name("언덕위");
        vo.setAccom_location("강원도 감자구");
        vo.setAccom_phone("010-1234-3333");
        vo.setAccom_amenities("감자무료");
        vo.setAccom_caution("감자만 먹을 수 있음");
        vo.setAccom_description("감자외 식사 금지");
        vo.setAccom_images_url("images/potato.jpg");
        service.insert(vo);
        service.getList().stream().forEach(System.out::println);
    }

    @Test
    public void testUpdate(){
        AccomVO vo = new AccomVO();
        vo.setAccom_id(8);
        vo.setAccom_name("에어비엔비아님");
        vo.setAccom_location("경기도 안양");
        vo.setAccom_phone("010-0321-5130");
        vo.setAccom_amenities("데시벨 조건 있음");
        vo.setAccom_caution("강아지는 데시벨 조건 없음");
        vo.setAccom_description("강아지를 위한 해먹 준비됨");
        vo.setAccom_images_url("images.notAirbnb.png");
        service.update(vo);
        service.getList().stream().forEach(System.out::println);
    }
}
