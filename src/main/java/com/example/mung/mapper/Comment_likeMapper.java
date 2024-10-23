package com.example.mung.mapper;

import com.example.mung.domain.Comment_likeDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Comment_likeMapper {

    // 좋아요/싫어요 추가
    @Insert("INSERT INTO comment_like (comment_id, user_id, type) " +
            "VALUES (#{comment_id}, #{user_id}, #{type})")
    @Options(useGeneratedKeys = true, keyProperty = "like_id")
    int insertLikeDislike(Comment_likeDTO commentLike);

    // 좋아요/싫어요 업데이트 (변경 시)
    @Update("UPDATE comment_like SET type = #{type} WHERE comment_id = #{comment_id} AND user_id = #{user_id}")
    int updateLikeDislike(Comment_likeDTO commentLike);

    // 특정 사용자가 특정 댓글에 대한 좋아요/싫어요 상태 확인
    @Select("SELECT * FROM comment_like WHERE comment_id = #{comment_id} AND user_id = #{user_id}")
    Comment_likeDTO findByCommentIdAndUserId(@Param("comment_id") int comment_id, @Param("user_id") int user_id);

    // 특정 댓글의 좋아요/싫어요 카운트
    @Select("SELECT COUNT(*) FROM comment_like WHERE comment_id = #{comment_id} AND type = 'LIKE'")
    int getLikeCount(@Param("comment_id") int comment_id);

    @Select("SELECT COUNT(*) FROM comment_like WHERE comment_id = #{comment_id} AND type = 'DISLIKE'")
    int getDislikeCount(@Param("comment_id") int comment_id);
}

