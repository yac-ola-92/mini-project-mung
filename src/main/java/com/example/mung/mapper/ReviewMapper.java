package com.example.mung.mapper;

import com.example.mung.domain.ReviewDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {
    @Select("SELECT  *  FROM review")
    List<ReviewDTO> getList();

    @Select("SELECT  * FROM review WHERE rating = #{rating}")
    ReviewDTO getByRating(@Param("rating")int rating);

    @Insert("INSERT INTO REVIEW (user_id, rv_id, rating, comment, created_at)" +
            "VALUES (#{user_id}, #{rv_id}, #{rating}, #{comment}, #{created_at})")
    boolean insert(ReviewDTO review);

    @Update("UPDATE review SET  user_id=#{user_id}, rating=#{rating}," +
            "comment=#{comment}, created_at=#{created_at}")
    boolean update(ReviewDTO review);

    @Delete("DELETE FROM review WHERE review_id = #{review_id}")
    boolean delete(@Param("review_id")int review_id);
}
