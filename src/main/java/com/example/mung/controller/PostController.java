package com.example.mung.controller;

import com.example.mung.domain.PostDTO;
import com.example.mung.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/paged")
    public List<PostDTO> getPagedPosts(@RequestParam int page, @RequestParam int size) {
        return postService.findAll(page, size);
    }

    @GetMapping("/category/{category}")
    public List<PostDTO> getPostsByCategory(@PathVariable String category) {
        return postService.findByCategory(PostDTO.Category.valueOf(category));
    }

    @GetMapping("/search")
    public List<PostDTO> searchPosts(@RequestParam String keyword, @RequestParam String type) {
        return postService.searchPosts(keyword, type);
    }

    @PostMapping
    public boolean createPost(@RequestBody PostDTO post) {
        return postService.register(post);
    }

    @PutMapping("/update/{post_id}")
    public boolean updatePost(@PathVariable int post_id, @RequestBody PostDTO post) {
        post.setPost_id(post_id);
        return postService.modify(post);
    }

    @DeleteMapping("/{post_id}")
    public boolean deletePost(@PathVariable int post_id) {
        return postService.remove(post_id);
    }

    //조회수 증가
    @PutMapping("/view/{post_id}")
    public boolean increaseViewCount(@PathVariable int post_id) {
        return postService.increaseViewCount(post_id);
    }

    //수정시 비밀번호 확인
    @PutMapping("/{post_id}")
    public boolean updatePost(@PathVariable int post_id, @RequestBody PostDTO post, @RequestParam String password) {
        if (postService.checkPassword(post_id, password)) {
            post.setPost_id(post_id);
            return postService.modify(post);
        }
        return false;
    }

    //삭제시 비밀번호 확인
    @DeleteMapping("/check/{post_id}")
    public boolean deletePost(@PathVariable int post_id, @RequestParam String password) {
        if (postService.checkPassword(post_id, password)) {
            return postService.remove(post_id);
        }
        return false;
    }

}
