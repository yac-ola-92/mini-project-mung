package com.example.mung.service;

import com.example.mung.domain.RoomVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomServiceImplTest {
    @Autowired
    private RoomService service;

    @Test //모든 숙소의 객실리스트 출력
    public void testGetList(){
        service.findAll().stream().forEach(System.out::println);
    }

   @Test //숙소 아이디로 객실 리스트 출력
   public void testGetListByAccom_id(){
       service.readByAccom_id(1).stream().forEach(System.out::println);
   }
   @Test // 견종으로 객실 출력
    public void testGetListByPet_kind(){
       service.readByPet_kind("소형견").stream().forEach(System.out::println);
   }

   @Test // 한 숙소에 객실 등록
    public void TestInsert(){
       RoomVO vo = new RoomVO();
       vo.setAccom_id(8);
       vo.setRoom_name("다다미룸");
       vo.setRoom_type("다다미");
       vo.setRoom_info("따뜻해여");
       vo.setRoom_amount(11);
       vo.setRoom_images_url("http://images.dadami.png");
       vo.setPet_kind("대형견");
       vo.setRoom_price(120000);
       service.register(vo);
       service.readByAccom_id(8).stream().forEach(System.out::println);
   }
    @Test // 한 숙소에 객실 등록
    public void TestUpdate(){
        RoomVO vo = new RoomVO();
        vo.setRoom_id(8);
        vo.setRoom_name("나홀로룸");
        vo.setRoom_type("싱글");
        vo.setRoom_info("OTT 다 됨");
        vo.setRoom_amount(15);
        vo.setRoom_images_url("http://images.couple.png");
        vo.setPet_kind("소형견");
        vo.setRoom_price(85000);
        service.modify(vo);
        service.readByAccom_id(8).stream().forEach(System.out::println);
    }

    @Test //객실 삭제
    public void TestDelete(){
        service.remove(10);
        service.readByAccom_id(8).stream().forEach(System.out::println);
    }

}
