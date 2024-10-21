package com.example.mung.mapper;

import com.example.mung.domain.Comment_likeDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Comment_likeMapper {

    // 모든 좋아요/싫어요 가져오기
    @Select("SELECT like_id, comment_id, user_id, type FROM comment_like")
    List<Comment_likeDTO> getAllLike();

    // 특정 유저가 선택한 좋아요/싫어요
    @Select("SELECT like_id, comment_id, user_id, type FROM comment_like WHERE user_id = #{user_id}")
    List<Comment_likeDTO> getLikeByUserId(@Param("user_id") int user_id);

    // 좋아요/싫어요 삽입
    @Insert("INSERT INTO comment_like (comment_id, user_id, type) " +
            "VALUES (#{comment_id}, #{user_id}, #{type})")
    @Options(useGeneratedKeys = true, keyProperty = "like_id") // 자동 생성된 키 반환
    int insert(Comment_likeDTO comment_like);

    // 좋아요/싫어요 변경
    @Update("UPDATE comment_like SET type = #{type} " +
            "WHERE comment_id = #{comment_id} AND user_id = #{user_id}")
    int update(Comment_likeDTO comment_like);

    // 좋아요/싫어요 취소
    @Delete("DELETE FROM comment_like WHERE comment_id = #{comment_id} AND user_id = #{user_id}")
    int delete(@Param("comment_id") int commentId, @Param("user_id") int userId);
}
