package com.example.mung.controller;

import com.example.mung.domain.ReviewVO;
import com.example.mung.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 리뷰 목록을 보여주기 위한 페이지
    @GetMapping("/reviews")
    public String showReviews(Model model) {
        List<ReviewVO> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);  // 리뷰 및 예약 정보
        return "postReview";
    }
}

