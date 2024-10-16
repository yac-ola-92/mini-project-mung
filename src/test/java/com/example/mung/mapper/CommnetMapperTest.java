
package com.example.mung.mapper;

import com.example.mung.domain.CommentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CommnetMapperTest {
    @Autowired
    private CommentMapper dao;

    @Test /*전체 댓글 조회*/
    void testAll() {dao.getList().stream().forEach(System.out::println);}

    @Test /* 특정 유저가 작성한 댓글 목록 조회*/
    void testFindByUser_id() {System.out.println(dao.getListByUserId(1));}

    @Test
    void testInsert() {
        CommentDTO comment = new CommentDTO();
        comment.setPost_id(1);
        comment.setContent("저도 여기 가봤어요!");
        comment.setCreated_at(LocalDateTime.now());
        comment.setUser_id(1);
        dao.insert(comment);
        dao.getList().stream().forEach(System.out::println);
    }

    @Test
    void testUpdate() {
        CommentDTO comment = new CommentDTO();
        comment.setComment_id(4);
        comment.setContent("저도 다음에 가보려구요");
        comment.setCreated_at(LocalDateTime.now());
        comment.setPost_id(1);
        comment.setUser_id(1);
        dao.update(comment);
        dao.getList().stream().forEach(System.out::println);
    }

    @Test
    void testDelete() {
        System.out.println(dao.delete(14));
        dao.getList().stream().forEach(System.out::println);
    }

}

