package com.example.mung;

import com.example.mung.domain.PostDTO;
import com.example.mung.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class PostserviceImplTests {
    @Autowired
    PostService postService;

    @Test // 게시글 전체 조회
    void testFindAll() {postService.findAll().stream().forEach(System.out::println);}

    @Test // 특정 게시글 조회
    void testReadByTitle() {System.out.println(postService.readByTitle("여기 좋아요!"));}

    @Test // 게시글 수정
    void testModify() {
        PostDTO post = new PostDTO();
        post.setPost_id(1);
        post.setTitle("여기 별로에요...");
        post.setContent("다시는 가고싶지 않아요..ㅠㅠ");
        post.setCategory(PostDTO.Category.general);
        post.setUpdated_at(LocalDateTime.now());
        post.setPassword("1234");
        post.setFiles(null);

        postService.modify(post);
        postService.findAll().stream().forEach(System.out::println);
    }

    @Test // 게시글 삭제
    void testRemove() {
        System.out.println(postService.remove(2));
        postService.findAll().stream().forEach(System.out::println);
    }

    @Test // 게시글 등록
    void testRegister() {
        PostDTO post = new PostDTO();
        post.setUser_id(1);
        post.setTitle("너무 좋아요!!");
        post.setContent("너무 좋아요! 돈도 시간도 아깝지 않아요!");
        post.setCategory(PostDTO.Category.rec);
        post.setCreated_at(LocalDateTime.now());
        post.setPassword("7890");
        post.setFiles(null);
        System.out.println(postService.register(post));
        postService.findAll().stream().forEach(System.out::println);
    }
}
