package com.example.mung.controller;

import com.example.mung.domain.Comment_likeDTO;
import com.example.mung.service.Comment_likeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/like")
public class Comment_likeController {
    private final Comment_likeService commentLikeService;

    public Comment_likeController(Comment_likeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }

    // 좋아/싫어 클릭시
    @PostMapping("/like/{comment_id}/{type}")
    @ResponseBody
    public ResponseEntity<Void> likeComment(@PathVariable int comment_id, @PathVariable String type, HttpSession session) {
        // 세션에서 사용자 정보 가져오기
        Integer user_id = (Integer) session.getAttribute("user_id");
        if (user_id == null) {
            return ResponseEntity.status(401).build(); // 로그인되지 않은 경우
        }

        // Comment_likeDTO 생성 및 설정
        Comment_likeDTO commentLike = new Comment_likeDTO();
        commentLike.setComment_id(comment_id);
        commentLike.setUser_id(user_id);

        // 타입을 enum으로 변환
        try {
            Comment_likeDTO.Type likeType = Comment_likeDTO.Type.valueOf(type.toUpperCase());
            commentLike.setType(likeType);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // 잘못된 타입 입력에 대한 예외 처리
        }

        // 서비스 호출
        commentLikeService.register(commentLike);
        return ResponseEntity.ok().build();
    }

    // 좋아/싫어 취소시
    @DeleteMapping("/like/{comment_id}")
    @ResponseBody
    public ResponseEntity<Void> unlikeComment(@PathVariable int comment_id, HttpSession session) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        if (user_id == null) {
            return ResponseEntity.status(401).build(); // 로그인되지 않은 경우
        }

        commentLikeService.remove(comment_id, user_id);
        return ResponseEntity.ok().build();
    }
}