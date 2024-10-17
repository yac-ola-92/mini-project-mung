package com.example.mung.controller;

import com.example.mung.domain.Comment_likeDTO;
import com.example.mung.service.Comment_likeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/like")
public class Comment_likeController {
    private final Comment_likeService commentLikeService;

    @Autowired
    public Comment_likeController(Comment_likeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }

    // 좋아/싫어 클릭시
    @PostMapping("/like/{comment_id}")
    @ResponseBody
    public ResponseEntity<Void> likeComment(@PathVariable int comment_id) {
        //임시
        int user_id = 1;

        Comment_likeDTO commentLike = new Comment_likeDTO();
        commentLike.setComment_id(comment_id);
        commentLike.setUser_id(user_id);

        commentLikeService.register(commentLike);
        return ResponseEntity.ok().build();
    }

    // 좋아/싫어 취소시
    @DeleteMapping("/like/{comment_id}")
    @ResponseBody
    public ResponseEntity<Void> unlikeComment(@PathVariable int comment_id) {
        //임시
        int user_id = 1;

        commentLikeService.remove(comment_id, user_id);
        return ResponseEntity.ok().build();
    }

}
