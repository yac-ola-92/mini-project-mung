package com.example.mung.controller;

import com.example.mung.domain.CommentDTO;
import com.example.mung.domain.UserVO;
import com.example.mung.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 댓글 추가
    @PostMapping("/post/{post_id}/comments/add")
    public String addComment(@PathVariable int post_id, @ModelAttribute CommentDTO commentDTO, HttpSession session) {
        UserVO userInfo = (UserVO) session.getAttribute("userInfo");
        if (userInfo == null) {
            return "redirect:/login";  // 로그인되지 않은 경우
        }

        // 댓글 작성자 정보 추가
        commentDTO.setUser_id(userInfo.getUser_id());
        commentDTO.setPost_id(post_id);

        // 댓글 저장 처리
        try {
            commentService.register(commentDTO);
        } catch (DataIntegrityViolationException e) {
            System.out.println("댓글 저장 중 오류 발생.");
            return "redirect:/error"; // 오류 페이지로 리다이렉트
        }

        return "redirect:/post/" + post_id; // 성공 시 게시물 페이지로 리다이렉트
    }
}
