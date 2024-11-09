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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
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
    public String addComment(@PathVariable int post_id, @ModelAttribute CommentDTO commentDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        UserVO userInfo = (UserVO) session.getAttribute("userInfo");
        if (userInfo == null) {
            redirectAttributes.addFlashAttribute("message", "로그인 후에 댓글을 작성할 수 있습니다.");
            return "redirect:/login";
        }

        // 댓글 작성자 정보 추가
        commentDTO.setUser_id(userInfo.getUser_id());
        commentDTO.setPost_id(post_id);

        try {
            // 댓글 저장 처리
            commentService.register(commentDTO);
            redirectAttributes.addFlashAttribute("message", "댓글이 성공적으로 추가되었습니다.");
        } catch (DataIntegrityViolationException e) {
            logger.error("댓글 저장 중 오류 발생.", e);
            redirectAttributes.addFlashAttribute("message", "댓글 저장에 실패했습니다. 입력 값을 확인해주세요.");
            return "redirect:/post/" + post_id;
        } catch (Exception e) {
            logger.error("예상치 못한 오류 발생", e);
            redirectAttributes.addFlashAttribute("message", "예상치 못한 오류가 발생했습니다.");
            return "redirect:/error";
        }

        return "redirect:/post/" + post_id; // 성공 시 게시물 페이지로 리다이렉트
    }
}
