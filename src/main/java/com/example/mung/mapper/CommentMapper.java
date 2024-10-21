package com.example.mung.mapper;

import com.example.mung.domain.CommentDTO;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CommentMapper {

    // 모든 댓글 가져오기
    @Select("SELECT comment_id, post_id, user_id, content, created_at, parent_comment_id FROM comment")
    List<CommentDTO> getAllComment();

    // 특정 유저가 작성한 댓글 목록 조회
    @Select("SELECT comment_id, post_id, user_id, content, created_at, parent_comment_id FROM comment WHERE user_id = #{user_id}")
    List<CommentDTO> getCommentByUserId(@Param("user_id") int user_id);

    // 특정 게시물에 달린 댓글 목록 조회
    @Select("SELECT comment_id, post_id, user_id, content, created_at, parent_comment_id FROM comment WHERE post_id = #{post_id}")
    List<CommentDTO> getCommentByPostId(@Param("post_id") int post_id);

    // 댓글 삽입 (일반 댓글 및 대댓글 처리 가능)
    @Insert("INSERT INTO comment (post_id, content, user_id, parent_comment_id) " +
            "VALUES (#{post_id}, #{content}, #{user_id}, #{parent_comment_id})")
    @Options(useGeneratedKeys = true, keyProperty = "comment_id")
    int insertComment(CommentDTO comment);

    // 댓글 수정
    @Update("UPDATE comment SET content = #{content}, updated_at = NOW() " +
            "WHERE comment_id = #{comment_id} AND post_id = #{post_id}")
    int updateComment(CommentDTO comment);

    // 댓글 삭제
    @Delete("DELETE FROM comment WHERE comment_id = #{comment_id}")
    int deleteComment(@Param("comment_id") int comment_id);

    // 부모 댓글 삭제 시 대댓글 삭제
    @Delete("DELETE FROM comment WHERE parent_comment_id = #{comment_id}")
    int deleteReplyByParent(@Param("comment_id") int comment_id);
}
