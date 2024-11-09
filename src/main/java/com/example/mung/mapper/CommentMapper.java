package com.example.mung.mapper;

import com.example.mung.domain.CommentDTO;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CommentMapper {

    // 모든 댓글 가져오기 (likeCount와 dislikeCount 포함)
    @Select("SELECT c.comment_id, c.post_id, c.user_id, c.content, c.created_at, u.nickname, " +
            "COALESCE(SUM(CASE WHEN cl.type = 'LIKE' THEN 1 ELSE 0 END), 0) AS likeCount, " +
            "COALESCE(SUM(CASE WHEN cl.type = 'DISLIKE' THEN 1 ELSE 0 END), 0) AS dislikeCount " +
            "FROM comment c " +
            "JOIN `user` u ON c.user_id = u.user_id " +
            "LEFT JOIN comment_like cl ON c.comment_id = cl.comment_id " +
            "GROUP BY c.comment_id, c.post_id, c.user_id, c.content, c.created_at, u.nickname")
    List<CommentDTO> getAllComment();

    // 특정 유저가 작성한 댓글 목록 조회 (likeCount와 dislikeCount 포함)
    @Select("SELECT c.comment_id, c.post_id, c.user_id, c.content, c.created_at, u.nickname, " +
            "COALESCE(SUM(CASE WHEN cl.type = 'LIKE' THEN 1 ELSE 0 END), 0) AS likeCount, " +
            "COALESCE(SUM(CASE WHEN cl.type = 'DISLIKE' THEN 1 ELSE 0 END), 0) AS dislikeCount " +
            "FROM comment c " +
            "JOIN `user` u ON c.user_id = u.user_id " +
            "LEFT JOIN comment_like cl ON c.comment_id = cl.comment_id " +
            "WHERE c.user_id = #{user_id} " +
            "GROUP BY c.comment_id, c.post_id, c.user_id, c.content, c.created_at, u.nickname")
    List<CommentDTO> getCommentByUserId(@Param("user_id") int user_id);

    // 특정 게시물에 달린 댓글 목록 조회 (likeCount와 dislikeCount 포함)
    @Select("SELECT c.comment_id, c.post_id, c.user_id, c.content, c.created_at, u.nickname, " +
            "COALESCE(SUM(CASE WHEN cl.type = 'LIKE' THEN 1 ELSE 0 END), 0) AS likeCount, " +
            "COALESCE(SUM(CASE WHEN cl.type = 'DISLIKE' THEN 1 ELSE 0 END), 0) AS dislikeCount " +
            "FROM comment c " +
            "JOIN `user` u ON c.user_id = u.user_id " +
            "LEFT JOIN comment_like cl ON c.comment_id = cl.comment_id " +
            "WHERE c.post_id = #{post_id} " +
            "GROUP BY c.comment_id, c.post_id, c.user_id, c.content, c.created_at, u.nickname")
    List<CommentDTO> getCommentsByPostId(@Param("post_id") int post_id);

    // 댓글 삽입
    @Insert("INSERT INTO comment (post_id, content, user_id) VALUES (#{post_id}, #{content}, #{user_id})")
    @Options(useGeneratedKeys = true, keyProperty = "comment_id")
    int insertComment(CommentDTO comment);

    // 댓글 수정
    @Update("UPDATE comment SET content = #{content}, updated_at = NOW() WHERE comment_id = #{comment_id} AND post_id = #{post_id}")
    int updateComment(CommentDTO comment);

    // 댓글 삭제
    @Delete("DELETE FROM comment WHERE comment_id = #{comment_id}")
    int deleteComment(@Param("comment_id") int comment_id);

    // 댓글 ID로 댓글 조회
    @Select("SELECT c.comment_id, c.post_id, c.user_id, c.content, c.created_at, u.nickname, " +
            "COALESCE(SUM(CASE WHEN cl.type = 'LIKE' THEN 1 ELSE 0 END), 0) AS likeCount, " +
            "COALESCE(SUM(CASE WHEN cl.type = 'DISLIKE' THEN 1 ELSE 0 END), 0) AS dislikeCount " +
            "FROM comment c " +
            "JOIN `user` u ON c.user_id = u.user_id " +
            "LEFT JOIN comment_like cl ON c.comment_id = cl.comment_id " +
            "WHERE c.comment_id = #{comment_id} " +
            "GROUP BY c.comment_id, c.post_id, c.user_id, c.content, c.created_at, u.nickname")
    CommentDTO findById(@Param("comment_id") int comment_id);
}
