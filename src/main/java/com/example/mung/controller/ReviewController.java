package com.example.mung.controller;

import com.example.mung.domain.ReviewDTO;
import com.example.mung.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getAllReview(Model model) {
        List<ReviewDTO> reviews = reviewService.findAll(); // 리뷰 리스트를 가져옴
        model.addAttribute("review", reviews); // 모델에 리뷰 리스트 추가
        return "reviewList"; // 뷰 이름 반환
    }

    @GetMapping("/{rating}")
    public String getReviewByRating(@PathVariable("rating") int rating, Model model) {
        List<ReviewDTO> reviews = reviewService.readByRating(rating);
        model.addAttribute("review", reviews);
        return "reviewList";
    }

    @PostMapping
    public String insertReview(@ModelAttribute ReviewDTO review) {
        reviewService.register(review);
        return "redirect:/review";
    }

    @PutMapping("/{review_id}")
    public String updateReview(@ModelAttribute ReviewDTO review) {
        reviewService.modify(review);
        return "redirect:/review";
    }

    @DeleteMapping("/{review_id}")
    public String deleteReview(@PathVariable("review_id") int review_id) {
        reviewService.remove(review_id);
        return "redirect:/review";
    }
}
