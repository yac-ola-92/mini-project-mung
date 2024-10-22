/*
package com.example.mung.mapper;

import com.example.mung.domain.CommentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CommentMapperTest {
    @Autowired
    private CommentMapper dao;


    @Test /*전체 댓글 조회*/
    void testAll() {dao.getAllComment().stream().forEach(System.out::println);}

    @Test /* 특정 유저가 작성한 댓글 목록 조회*/
    void testFindByUser_id() {System.out.println(dao.getCommentByUserId(1));}


    @Test
    void testInsert() {
        CommentDTO comment = new CommentDTO();
        comment.setPost_id(1);
        comment.setContent("저도 여기 가봤어요!");
/*        comment.setCreated_at(LocalDateTime.now());*/
        comment.setUser_id(1);
        dao.insertComment(comment);
        dao.getAllComment().stream().forEach(System.out::println);
    }

    @Test
    void testUpdate() {
        CommentDTO comment = new CommentDTO();
        comment.setComment_id(4);
        comment.setContent("저도 다음에 가보려구요");
/*        comment.setCreated_at(LocalDateTime.now());*/
        comment.setPost_id(1);
        comment.setUser_id(1);
        dao.updateComment(comment);
        dao.getAllComment().stream().forEach(System.out::println);
    }

    @Test
    void testDelete() {
        System.out.println(dao.deleteComment(14));
        dao.getAllComment().stream().forEach(System.out::println);
    }

    @Test
    void testInsertParent() {
        CommentDTO comment = new CommentDTO();
        comment.setPost_id(1);
        comment.setContent("너무 좋죠?");
/*        comment.setCreated_at(LocalDateTime.now());*/
        comment.setUser_id(1);
        comment.setParent_comment_id(4); // 부모 댓글 아이디 설정
        dao.insertComment(comment);
        dao.getAllComment().stream().forEach(System.out::println);
    }

}
*/
