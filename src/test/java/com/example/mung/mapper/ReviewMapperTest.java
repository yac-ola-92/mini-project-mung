package com.example.mung.mapper;

import com.example.mung.domain.ReviewDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ReviewMapperTest {
    @Autowired
    private ReviewMapper dao;

    @Test
    void testAll() {dao.getList().forEach(System.out::println);}

    @Test
    void testSelectByRating() {System.out.println(dao.getByRating(3));}

    @Test
    void testInsert() {
        ReviewDTO dto = new ReviewDTO();
        dto.setUser_id(1);
        dto.setRv_id(1);
        dto.setRating(1);
        dto.setComment("더러워요. 돈 아까워요.");
        dto.setCreated_at(LocalDateTime.now());
        System.out.println(dao.insert(dto));
        dao.getList().forEach(System.out::println);
    }

    @Test
    void testUpdate() {
        ReviewDTO dto = new ReviewDTO();
        dto.setReview_id(2);
        dto.setRating(3);
        dto.setComment("나쁘지 않았어요.");
        dto.setCreated_at(LocalDateTime.now());

        dao.update(dto);
        dao.getList().forEach(System.out::println);
    }

    @Test
    void testDelete() {
        ReviewDTO dto = new ReviewDTO();
        System.out.println(dao.delete(1));
        dao.getList().forEach(System.out::println);
    }

}
