package com.example.mung.service;


import com.example.mung.domain.ReviewDTO;
import com.example.mung.domain.ReviewVO;

import java.util.List;

public interface ReviewService {
    List<ReviewVO> getAllReviews();
}

