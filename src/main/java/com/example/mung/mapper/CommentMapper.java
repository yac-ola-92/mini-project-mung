package com.example.mung.mapper;

import com.example.mung.domain.CommentDTO;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("SELECT * FROM comment")
    List<CommentDTO> getList();

    // 특정 유저가 작성한 댓글 목록 조회
    @Select("SELECT * FROM comment WHERE user_id = #{user_id}")
    List<CommentDTO> getListByUserId(@Param("user_id") int user_id);

    // 특정 게시물에 달린 댓글 목록 조회
    @Select("SELECT * FROM comment WHERE post_id = #{post_id}")
    List<CommentDTO> getListByPostId(@Param("post_id") int post_id);

    // 댓글 삽입
    @Insert("INSERT INTO comment (post_id, content, created_at, user_id) " +
            "VALUES (#{post_id}, #{content}, #{created_at}, #{user_id})")
    @Options(useGeneratedKeys = true, keyProperty = "comment_id")
    boolean insert(CommentDTO comment);

    // 댓글 수정
    @Update("UPDATE comment SET content = #{content}, created_at = #{created_at} " +
            "WHERE comment_id = #{comment_id} AND post_id = #{post_id}")
    boolean update(CommentDTO comment);

    // 댓글 삭제
    @Delete("DELETE FROM comment WHERE comment_id = #{comment_id}")
    boolean delete(@Param("comment_id") int comment_id);

}
