package com.example.mung.controller;

import com.example.mung.domain.CommentDTO;
import com.example.mung.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 리스트 조회
    @GetMapping
    public String getAllComments(Model model) {
        List<CommentDTO> comments = commentService.findAll();
        model.addAttribute("comments", comments);
        return "comments/list";
    }

    // 특정 유저가 작성한 댓글 목록 조회
    @GetMapping("/user/{user_id}")
    public String getPostByUserId(@PathVariable int user_id, Model model) {
        List<CommentDTO> comments = commentService.readByUserId(user_id);
        model.addAttribute("comments", comments);
        return "comments/user";
    }

    // 특정 게시물에 달린 댓글 목록 조회
    @GetMapping("/post/{post_id}")
    public String getPostByPostId(@PathVariable int post_id, Model model) {
        List<CommentDTO> comments = commentService.readByPostId(post_id);
        model.addAttribute("comments", comments);
        return "comments";
    }

    // 댓글 등록
    @PostMapping
    public String insertComment(@ModelAttribute CommentDTO comment) {
        commentService.register(comment);
        return "redirect:/comments";
    }

    // 댓글 수정
    @PutMapping("/{comment_id}")
    public String updateComment(@PathVariable int comment_id, @ModelAttribute CommentDTO comment) {
        comment.setComment_id(comment_id); // comment_id 설정
        commentService.modify(comment);
        return "redirect:/comments/" + comment_id;
    }

    // 댓글 삭제
    @DeleteMapping("/{comment_id}")
    public String deleteComment(@PathVariable int comment_id) {
        commentService.remove(comment_id);
        return "redirect:/comments";
    }
}
