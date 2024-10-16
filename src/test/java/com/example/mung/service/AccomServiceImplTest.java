package com.example.mung.service;

import com.example.mung.domain.AccomVO;
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
}
