//package com.example.mung.service;
//
//import com.example.mung.domain.CommentDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@SpringBootTest
//public class CommentServiceImplTest {
//    @Autowired
//    private CommentService commentService;
//
//    @Test
//    void testFindAll() {commentService.findAll().stream().forEach(System.out::println);}
//
//    @Test
//    void testReadByUserId(){
//        int user_id = 1;
//        List<CommentDTO> comment = commentService.readByUserId(user_id);
//        comment.stream().forEach(System.out::println);
//    }
//
//    @Test
//    void testReadByPostId() {
//        int post_id = 1;
//        List<CommentDTO> comment = commentService.readByPostId(post_id);
//        comment.stream().forEach(System.out::println);
//    }
//
//    @Test
//    void testRegister() {
//        CommentDTO comment = new CommentDTO();
//        comment.setPost_id(1);
//        comment.setContent("담에 또 가야지~~");
//        comment.setCreated_at(LocalDateTime.now());
//        comment.setUser_id(1);
//        System.out.println(commentService.register(comment));
//        commentService.findAll().stream().forEach(System.out::println);
//    }
//
//    @Test
//    void testUpdate() {
//        CommentDTO comment = new CommentDTO();
//        comment.setComment_id(4); // 기존 ID
//        comment.setPost_id(1); // 관련된 post_id
//        comment.setContent("에휴...");
//        comment.setCreated_at(LocalDateTime.now());
//        System.out.println(commentService.modify(comment));
//        commentService.findAll().stream().forEach(System.out::println);
//    }
//
//    @Test
//    void testDelete() {
//        System.out.println(commentService.remove(12));
//        commentService.findAll().stream().forEach(System.out::println);
//    }
//}
