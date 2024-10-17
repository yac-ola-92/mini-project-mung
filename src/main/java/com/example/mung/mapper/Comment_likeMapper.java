package com.example.mung.mapper;

import com.example.mung.domain.Comment_likeDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Comment_likeMapper {

    @Select("SELECT  * FROM comment_like")
    List<Comment_likeDTO> getAllLike();

    // 특정 유저가 선택한 댓글 좋아요, 싫어요
    @Select("SELECT  * FROM comment_like WHERE user_id = #{user_id}")
    List<Comment_likeDTO> getLikeByUserId(@Param("userId") int user_id);

    @Insert("INSERT INTO comment_like (comment_id, user_id, type) " +
            "VALUES (#{comment_id}, #{user_id}, #{type})")
    boolean insert(Comment_likeDTO comment_like);

    // 좋아요, 싫어요 변경
    @Update("UPDATE comment_like SET type = #{type} " +
            "WHERE comment_id = #{comment_id} AND user_id = #{user_id}")
    boolean update(Comment_likeDTO comment_like);

    // 좋아요, 싫어요 취소
    @Delete("DELETE FROM comment_like WHERE comment_id = #{comment_id} AND user_id = #{user_id}")
    boolean delete(@Param("comment_id") int commentId, @Param("user_id") int userId);


}
