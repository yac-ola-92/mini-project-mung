package com.example.mung.mapper;

import com.example.mung.domain.RoomVO;
import com.example.mung.mapper.RoomMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomMapperTest {
    @Autowired
    private RoomMapper mapper;

    @Test
    public void testGetList(){
        mapper.getList().stream().forEach(System.out::println);
    }

    @Test
    public void testGetListByAccom_id(){
        mapper.getListByAccom_id(1).stream().forEach(System.out::println);
    }

    @Test
    public void testGetListByPet_kind(){
        mapper.getListByPet_kind("소형견").stream().forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        RoomVO vo = new RoomVO();
        vo.setAccom_id(1);
        vo.setRoom_name("패밀리룸");
        vo.setRoom_type("패밀리");
        vo.setRoom_price(150000);
        vo.setRoom_images_url("images.room2_1, images.room2_2");
        vo.setRoom_info("그냥뭐s");
        vo.setRoom_amount(4);
        vo.setPet_kind("소형견");
        mapper.insert(vo);
        mapper.getListByAccom_id(1).stream().forEach(System.out::println);
    }

    @Test
    public void testUpdate(){
        RoomVO vo = new RoomVO();
        vo.setRoom_id(5);
        vo.setRoom_name("우리가족룸");
        vo.setRoom_type("패밀리");
        vo.setRoom_price(170000);
        vo.setRoom_images_url("familyroom1_1.jpg ,familyroom1_2.jpg");
        vo.setRoom_info("오직 가족만");
        vo.setRoom_amount(7);
        vo.setPet_kind("소형견");
        System.out.println(vo);
        mapper.update(vo);
        mapper.getListByAccom_id(1).stream().forEach(System.out::println);
    }

    @Test
    public void testDelete() {
        mapper.delete(3);
        mapper.getListByAccom_id(1).stream().forEach(System.out::println);
    }
}