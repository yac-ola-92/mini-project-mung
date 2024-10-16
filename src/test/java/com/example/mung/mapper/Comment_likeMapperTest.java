package com.example.mung.mapper;

import com.example.mung.domain.Comment_likeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Comment_likeMapperTest {
    @Autowired
    private Comment_likeMapper dao;

    @Test
    void testAllLike() {dao.getAllLike().stream().forEach(System.out::println);}

    @Test
    void testSelectByUserId() {System.out.println(dao.getLikeByUserId(1));}

    @Test
    void testInsert() {
        Comment_likeDTO dto = new Comment_likeDTO();
        dto.setComment_id(2);
        dto.setUser_id(1);
        dto.setType(Comment_likeDTO.Type.LIKE);
        System.out.println(dao.insert(dto));
        dao.getAllLike().stream().forEach(System.out::println);
    }

    @Test
    void testUpdate() {
        Comment_likeDTO dto = new Comment_likeDTO();
        dto.setComment_id(1);
        dto.setUser_id(1);
        dto.setType(Comment_likeDTO.Type.DISLIKE);
        System.out.println(dao.update(dto));
        dao.getAllLike().stream().forEach(System.out::println);
    }

    @Test
    void testDelete() {
        System.out.println(dao.delete(2, 1));
        dao.getAllLike().stream().forEach(System.out::println);
    }

}
