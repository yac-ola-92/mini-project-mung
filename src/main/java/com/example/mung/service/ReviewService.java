package com.example.mung.service;


import com.example.mung.domain.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> findAll();
    List<ReviewDTO> readByRating(int rating);
    boolean register(ReviewDTO review);
    boolean modify(ReviewDTO review);
    boolean remove(int review_id);
}
