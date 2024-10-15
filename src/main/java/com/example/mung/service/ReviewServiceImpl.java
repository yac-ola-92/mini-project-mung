package com.example.mung.service;

import com.example.mung.domain.ReviewDTO;
import com.example.mung.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDTO> findAll() {
        return reviewMapper.getList();
    }

    @Override
    public List<ReviewDTO> readByRating(int rating) {
        return reviewMapper.getByRating(rating);
    }

    @Override
    public boolean register (ReviewDTO review) {
        return reviewMapper.insert(review);
    }

    @Override
    public boolean modify (ReviewDTO review) {
        return reviewMapper.update(review);
    }

    @Override
    public boolean remove (int review_id) {
        return reviewMapper.delete(review_id);
    }

}
