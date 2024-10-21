package com.example.mung.controller;

import com.example.mung.domain.CommentDTO;
import com.example.mung.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/post/{post_id}")
    public String getCommentsByPostId(@PathVariable int post_id, Model model) {
        List<CommentDTO> comments = commentService.readByPostId(post_id);
        model.addAttribute("comments", comments);
        return "comments/post"; // 댓글 목록 표시
    }

    // 댓글 및 대댓글 등록
    @PostMapping("/add")
    public String insertComment(@ModelAttribute @Valid CommentDTO comment,
                                HttpSession session,
                                @RequestParam(required = false) Integer parentCommentId,
                                Model model) {
        Integer user_id = (Integer) session.getAttribute("user_id");

        if (user_id == null) {
            model.addAttribute("msg", "로그인 후 댓글을 작성할 수 있습니다.");
            model.addAttribute("url", "/login");
            return "alert"; // alert.html 파일로 이동
        }

        comment.setUser_id(user_id);
        comment.setParent_comment_id(parentCommentId); // 부모 댓글 ID 설정 (null이면 일반 댓글, 값이 있으면 대댓글)
        commentService.register(comment);

        return "redirect:/post/" + comment.getPost_id(); // 댓글 작성 후 게시글 상세 페이지로 리디렉트
    }

    // 댓글 수정
    @PostMapping("/update/{comment_id}")
    public String updateComment(@PathVariable int comment_id,
                                @ModelAttribute @Valid CommentDTO comment,
                                HttpSession session,
                                Model model) {
        Integer user_id = (Integer) session.getAttribute("user_id");

        if (user_id == null) {
            model.addAttribute("msg", "로그인 후 댓글을 수정할 수 있습니다.");
            model.addAttribute("url", "/login");
            return "alert"; // alert.html 파일로 이동
        }

        comment.setComment_id(comment_id);
        comment.setUser_id(user_id); // 세션에서 가져온 user_id 설정
        commentService.modify(comment);

        return "redirect:/post/" + comment.getPost_id(); // 수정 후 다시 상세 페이지로 리디렉션
    }

    // 댓글 삭제
    @DeleteMapping("/{comment_id}")
    public String deleteComment(@PathVariable int comment_id,
                                @RequestParam int post_id,
                                HttpSession session,
                                Model model) {
        Integer user_id = (Integer) session.getAttribute("user_id");

        if (user_id == null) {
            model.addAttribute("msg", "로그인 후 댓글을 삭제할 수 있습니다.");
            model.addAttribute("url", "/login");
            return "alert"; // alert.html 파일로 이동
        }

        commentService.remove(comment_id);
        return "redirect:/post/" + post_id; // 삭제 후 다시 상세 페이지로 리디렉션
    }
}
