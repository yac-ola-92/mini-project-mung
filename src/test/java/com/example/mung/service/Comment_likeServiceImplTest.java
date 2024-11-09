//package com.example.mung.service;
//
//import com.example.mung.domain.Comment_likeDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//public class Comment_likeServiceImplTest {
//    @Autowired
//    private Comment_likeService comment_likeService;
//
//    @Test
//    void testFindAll() {comment_likeService.findAll().stream().forEach(System.out::println);}
//
//    @Test
//    void testReadByTitle() {System.out.println(comment_likeService.readByUserId(1));}
//
//    @Test
//    @Transactional
//    void testRegister() {
//        Comment_likeDTO comment_like = new Comment_likeDTO();
//        comment_like.setComment_id(11);
//        comment_like.setUser_id(1);
//        comment_like.setType(Comment_likeDTO.Type.DISLIKE);
//        System.out.println(comment_likeService.register(comment_like));
//        comment_likeService.findAll().stream().forEach(System.out::println);
//    }
//
//    @Test
//    void testUpdate() {
//        Comment_likeDTO comment_like = new Comment_likeDTO();
//        comment_like.setComment_id(11);
//        comment_like.setUser_id(1);
//        comment_like.setType(Comment_likeDTO.Type.LIKE);
//        System.out.println(comment_likeService.register(comment_like));
//        comment_likeService.findAll().stream().forEach(System.out::println);
//    }
//
//    @Test
//    @Transactional
//    void testDelete() {
//        System.out.println(comment_likeService.remove(4,1));
//        comment_likeService.findAll().stream().forEach(System.out::println);
//    }
//}
