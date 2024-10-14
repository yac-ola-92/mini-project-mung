package com.example.mung;

import com.example.mung.domain.ReviewDTO;
import com.example.mung.mapper.ReviewMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ReviewMapperTest {
    @Autowired
    ReviewMapper dao;

    @Test
    void testAll() {dao.getList().forEach(System.out::println);}

    @Test
    void testSelectByRating() {System.out.println(dao.getByRating(5));}

    @Test
    void testInsert() {
        ReviewDTO dto = new ReviewDTO();
        dto.setUser_id(1);
        dto.setRv_id(1);
        dto.setRating(1);
        dto.setComment("더러워요. 돈 아까워요.");
        System.out.println(dao.insert(dto));
        dao.getList().forEach(System.out::println);
    }

    @Test
    void testUpdate() {
        ReviewDTO dto = new ReviewDTO();
        dto.setUser_id(1);
        dto.setRating(3);
        dto.setComment("나쁘지 않았어요.");
        dto.setCreated_at(LocalDateTime.now());

        dao.update(dto);
        dao.getList().forEach(System.out::println);
    }

}
