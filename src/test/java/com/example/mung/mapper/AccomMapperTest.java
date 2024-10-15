package com.example.mung.mapper;

import com.example.mung.domain.AccomVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccomMapperTest {
    @Autowired
    private  AccomMapper  mapper;

    @Test
    public void testGetList(){
        mapper.getList().stream().forEach(System.out::println);
    }
    @Test
    public void testGetListByLocation(){
        System.out.println(mapper.getListByLocation("강원"));
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
        System.out.println(mapper.insert(vo));
        mapper.getList().stream().forEach(System.out::println);
    }

   @Test
    public void testUpdate(){
       AccomVO vo = new AccomVO();
      vo.setAccom_name("강남호텔");
       vo.setAccom_location("서울시 강남구");
       vo.setAccom_phone("02-9876-5432");
       vo.setAccom_amenities("입실 전 몰라");
       vo.setAccom_caution("3시 체크인");
       vo.setAccom_description("11시 체크아웃");
       vo.setAccom_images_url("images/example.png");
       /*AccomVO vo = new AccomVO("강남호텔","서울시 강남구","02-9876-5432","입실 전 몰라","3시 체크인","11시 체크아웃","images/example.png");*/
       mapper.update(1, vo);
        mapper.getList().stream().forEach(System.out::println);
    }


   @Test
    public void testDelete(){
        mapper.delete(3);
        mapper.getList().stream().forEach(System.out::println);
    }
}
