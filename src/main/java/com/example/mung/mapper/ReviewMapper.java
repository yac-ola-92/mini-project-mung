package com.example.mung.mapper;

import com.example.mung.domain.ReviewDTO;
import com.example.mung.domain.ReviewVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {
    @Select("SELECT r.review_id AS review_id, r.user_id AS user_id, u.nickname AS nickname, " +
            "res.rv_id AS rv_id,  r.rating AS rating, r.comment AS comment, " +
            "r.created_at AS created_at, rm.room_name AS room_name, " +
            "a.accom_name AS accom_name,  a.accom_images_url AS accom_images_url " +
            "FROM REVIEW r " +
            "JOIN user u ON r.user_id = u.user_id " +
            "JOIN RESERVATION res ON r.rv_id = res.rv_id " +
            "JOIN ROOM rm ON res.room_id = rm.room_id " +
            "JOIN ACCOMMODATION a ON res.accom_id = a.accom_id")
    List<ReviewVO> getAllReviews();

}


