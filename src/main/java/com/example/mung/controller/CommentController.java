package com.example.mung.controller;

import com.example.mung.domain.CommentDTO;
import com.example.mung.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 특정 게시물의 댓글 리스트 조회
    @GetMapping("/post/{post_id}/comments")
    public String getCommentsByPostId(@PathVariable int post_id, Model model) {
        List<CommentDTO> comments = commentService.readByPostId(post_id);
        model.addAttribute("comments", comments);
        return "comments/post"; // 댓글 목록을 표시하는 템플릿
    }

    // 댓글 및 대댓글 등록
    @PostMapping("/post/{post_id}/comments/add")
    public ResponseEntity<String> insertComment(@PathVariable int post_id,
                                                @ModelAttribute @Valid CommentDTO comment,
                                                HttpSession session,
                                                @RequestParam(required = false) Integer parentCommentId) {
        Integer user_id = (Integer) session.getAttribute("user_id");

        // 로그인되지 않은 경우 경고창을 띄운 후 리다이렉트하는 JavaScript 코드 반환
        if (user_id == null) {
            return ResponseEntity.ok("<script>alert('로그인 후 댓글을 작성할 수 있습니다.'); window.location.href='/login';</script>");
        }

        comment.setUser_id(user_id);
        comment.setPost_id(post_id);
        comment.setParent_comment_id(parentCommentId);
        commentService.register(comment);

        // 댓글 작성 후 게시글 페이지로 리다이렉트
        return ResponseEntity.ok("<script>alert('댓글이 성공적으로 작성되었습니다.'); window.location.href='/post/" + post_id + "';</script>");
    }

    //댓글 수정
    @PostMapping("/post/{post_id}/comments/update/{comment_id}")
    public ResponseEntity<String> updateComment(@PathVariable int post_id,
                                                @PathVariable int comment_id,
                                                @ModelAttribute @Valid CommentDTO comment,
                                                HttpSession session) {
        Integer user_id = (Integer) session.getAttribute("user_id");

        // 로그인되지 않은 경우 경고창 띄우고 리다이렉트
        if (user_id == null) {
            return ResponseEntity.ok("<script>alert('로그인 후 댓글을 수정할 수 있습니다.'); window.location.href='/login';</script>");
        }

        // 댓글 작성자 확인
        CommentDTO existingComment = commentService.findById(comment_id);
        if (existingComment == null || !Objects.equals(existingComment.getUser_id(), user_id)) {
            return ResponseEntity.ok("<script>alert('본인의 댓글만 수정할 수 있습니다.'); window.location.href='/post/" + post_id + "';</script>");
        }

        comment.setComment_id(comment_id);
        comment.setUser_id(user_id);
        comment.setPost_id(post_id);
        commentService.modify(comment);

        return ResponseEntity.ok("<script>alert('댓글이 성공적으로 수정되었습니다.'); window.location.href='/post/" + post_id + "';</script>");
    }

    @DeleteMapping("/post/{post_id}/comments/{comment_id}")
    public ResponseEntity<String> deleteComment(@PathVariable int post_id,
                                                @PathVariable int comment_id,
                                                HttpSession session) {
        Integer user_id = (Integer) session.getAttribute("user_id");

        // 로그인되지 않은 경우 경고창 띄우고 리다이렉트
        if (user_id == null) {
            return ResponseEntity.ok("<script>alert('로그인 후 댓글을 삭제할 수 있습니다.'); window.location.href='/login';</script>");
        }

        // 댓글 작성자 확인
        CommentDTO existingComment = commentService.findById(comment_id);
        if (existingComment == null || !Objects.equals(existingComment.getUser_id(), user_id)) {
            return ResponseEntity.ok("<script>alert('본인의 댓글만 삭제할 수 있습니다.'); window.location.href='/post/" + post_id + "';</script>");
        }

        commentService.remove(comment_id);
        return ResponseEntity.ok("<script>alert('댓글이 성공적으로 삭제되었습니다.'); window.location.href='/post/" + post_id + "';</script>");
    }
}
