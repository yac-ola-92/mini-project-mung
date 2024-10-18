package com.example.mung.mapper;

import com.example.mung.domain.AccomVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccomMapperTest {
    @Autowired
    private  AccomMapper  accomDAO;
    // autowired가 객체를 생성해서 가져오는데 private를 안하면
    // 패키지안의 다른 클래스에서도 사용가능하기 때문에
    // 이 페이지 안에서만 사용할 수 있게  함

    @Test
    public void testGetList(){
        accomDAO.getList().stream().forEach(System.out::println);
    }

    @Test
    public void testGetOnetByLocation(){
        accomDAO.getOnetByLocation("강원").stream().forEach(System.out::println);
    }

    @Test
    public void testGetOneByUserAndAccom_name(){
        AccomVO vo = new AccomVO();
        vo.setUser_id(1);
        vo.setAccom_name("언덕위");
        accomDAO.getoneByUserAndAccom_name(vo).stream().forEach(System.out::println);
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
        System.out.println(accomDAO.insert(vo));
        accomDAO.getList().stream().forEach(System.out::println);
    }

   @Test
    public void testUpdate(){
       AccomVO vo = new AccomVO();
       vo.setAccom_id(1);
      vo.setAccom_name("강하다호텔");
       vo.setAccom_location("서울시 강남구");
       vo.setAccom_phone("02-9876-5432");
       vo.setAccom_amenities("입실 전 몰라");
       vo.setAccom_caution("3시 체크인");
       vo.setAccom_description("11시 체크아웃");
       vo.setAccom_images_url("images/example.png");
       accomDAO.update(vo);
       accomDAO.getList().stream().forEach(System.out::println);
    }


   @Test
    public void testDelete(){
       accomDAO.delete(12);
       accomDAO.getList().stream().forEach(System.out::println);
    }
}
