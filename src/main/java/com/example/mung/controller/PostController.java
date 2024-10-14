package com.example.mung.controller;

import com.example.mung.domain.PostDTO;
import com.example.mung.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getAllPost(Model model) {
        List<PostDTO> post = postService.findAll();
        model.addAttribute("post", post);
        return "postList";
    }

    @GetMapping("/{title}")
    public String getPostByTitle(@PathVariable("title") String title, Model model) {
        PostDTO post =postService.readByTitle(title);
        model.addAttribute("post", post);
        return "postDetail";
    }

    @PostMapping
    public String insertPost(@ModelAttribute PostDTO post) {
        postService.register(post);
        return "redirect:/post";
    }

    @PutMapping("/{id}")
    public String updatePost(@ModelAttribute PostDTO post) {
        postService.modify(post);
        return "redirect:/post";
    }

    @DeleteMapping("/{post_id}")
    public String deletePost(@PathVariable("post_id") int post_id) {
        postService.remove(post_id);
        return "redirect:/post";
    }
}
