package com.example.mung.mapper;

import com.example.mung.domain.RoomVO;
<<<<<<< HEAD
=======
import com.example.mung.mapper.RoomMapper;
>>>>>>> 3b0756714de4ffa9efe73db0d91b2d2b41c5e61e
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomMapperTest {
    @Autowired
    private RoomMapper mapper;

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
<<<<<<< HEAD
        vo.setRoom_info("그냥뭐");
=======
        vo.setRoom_info("그냥뭐s");
>>>>>>> 3b0756714de4ffa9efe73db0d91b2d2b41c5e61e
        vo.setRoom_amount(4);
        vo.setPet_kind("소형견");
        mapper.insert(vo);
        mapper.getListByAccom_id(1).stream().forEach(System.out::println);
    }

<<<<<<< HEAD
@Test
=======
    @Test
>>>>>>> 3b0756714de4ffa9efe73db0d91b2d2b41c5e61e
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
<<<<<<< HEAD
         System.out.println(vo);
        mapper.update(vo);
       mapper.getListByAccom_id(1).stream().forEach(System.out::println);
=======
        System.out.println(vo);
        mapper.update(vo);
        mapper.getListByAccom_id(1).stream().forEach(System.out::println);
>>>>>>> 3b0756714de4ffa9efe73db0d91b2d2b41c5e61e
    }

    @Test
    public void testDelete() {
        mapper.delete(2);
        mapper.getListByAccom_id(1).stream().forEach(System.out::println);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 3b0756714de4ffa9efe73db0d91b2d2b41c5e61e
