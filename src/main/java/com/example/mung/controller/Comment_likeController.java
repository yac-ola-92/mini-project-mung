package com.example.mung.controller;

import com.example.mung.domain.Comment_likeDTO;
import com.example.mung.domain.UserVO;
import com.example.mung.service.Comment_likeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/like")
public class Comment_likeController {

    private final Comment_likeService commentLikeService;

    @Autowired
    public Comment_likeController(Comment_likeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }

    // 좋아요/싫어요 처리
    @PostMapping("/{type}/{comment_id}")
    public ResponseEntity<?> likeOrDislike(@PathVariable String type, @PathVariable int comment_id, HttpSession session) {
        UserVO userInfo = (UserVO) session.getAttribute("userInfo");
        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 후 사용할 수 있습니다.");
        }

        Comment_likeDTO commentLikeDTO = new Comment_likeDTO();
        commentLikeDTO.setComment_id(comment_id);
        commentLikeDTO.setUser_id(userInfo.getUser_id());

        if ("like".equalsIgnoreCase(type)) {
            commentLikeDTO.setType(Comment_likeDTO.Type.LIKE);
        } else if ("dislike".equalsIgnoreCase(type)) {
            commentLikeDTO.setType(Comment_likeDTO.Type.DISLIKE);
        } else {
            return ResponseEntity.badRequest().body("잘못된 타입입니다.");
        }

        // 좋아요/싫어요 처리 후 결과 반환
        Map<String, Integer> response = commentLikeService.likeOrDislike(commentLikeDTO);
        return ResponseEntity.ok(response);
    }

    // 댓글의 좋아요/싫어요 수 조회
    @GetMapping("/{commentId}/count")
    public ResponseEntity<Map<String, Integer>> getCounts(@PathVariable int commentId) {
        Map<String, Integer> counts = commentLikeService.getLikeAndDislikeCounts(commentId);
        return ResponseEntity.ok(counts);
    }
}
