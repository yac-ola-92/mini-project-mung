package com.example.mung.mapper;

import com.example.mung.domain.PostDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {

    // 모든 게시글 조회 (nickname 포함)
    @Select("SELECT p.post_id, p.user_id, p.title, p.content, p.category, p.created_at, p.updated_at, p.view_count, u.nickname " +
            "FROM post p " +
            "JOIN user u ON p.user_id = u.user_id")
    List<PostDTO> getList();

    // 페이징 처리된 게시글 조회 (nickname 포함)
    @Select("SELECT p.post_id, p.user_id, p.title, p.content, p.category, p.created_at, p.updated_at, p.view_count, u.nickname " +
            "FROM post p " +
            "JOIN user u ON p.user_id = u.user_id " +
            "LIMIT #{size} OFFSET #{offset}")
    List<PostDTO> getPagedPost(@Param("size") int size, @Param("offset") int offset);

    // 카테고리별 게시글 조회 (nickname 포함)
    @Select("SELECT p.post_id, p.user_id, p.title, p.content, p.category, p.created_at, p.updated_at, p.view_count, u.nickname " +
            "FROM post p " +
            "JOIN user u ON p.user_id = u.user_id " +
            "WHERE p.category = #{category}")
    List<PostDTO> getPostByCategory(@Param("category") String category);

    // 게시글 수정
    @Update("UPDATE post SET title = #{title}, content = #{content}, category = #{category}, " +
            "updated_at = #{updated_at}, password = #{password}, files = #{files} WHERE post_id = #{post_id}")
    int update(PostDTO post);

    // 게시글 등록
    @Insert("INSERT INTO posts (user_id, nickname, title, content, files, created_at, password, category)\n" +
            "    VALUES (#{user_id}, #{nickname}, #{title}, #{content}, #{files}, NOW(), #{password}, #{category})")
    @Options(useGeneratedKeys = true, keyProperty = "post_id")
    int insert(PostDTO post);

    // 게시글 삭제
    @Delete("DELETE FROM post WHERE post_id = #{post_id}")
    int delete(@Param("post_id") int post_id);

    // 조회수 증가
    @Update("UPDATE post SET view_count = view_count + 1 WHERE post_id = #{post_id}")
    int increaseViewCount(@Param("post_id") int post_id);

    // 게시글 검색 (타입별 검색)
    @Select("SELECT p.post_id, p.user_id, p.title, p.content, p.category, p.created_at, p.updated_at, p.view_count, u.nickname " +
            "FROM post p " +
            "JOIN user u ON p.user_id = u.user_id " +
            "WHERE p.title LIKE CONCAT('%', #{keyword}, '%')")
    List<PostDTO> findByTitle(@Param("keyword") String keyword);

    // 내용으로 검색
    @Select("SELECT p.post_id, p.user_id, p.title, p.content, p.category, p.created_at, p.updated_at, p.view_count, u.nickname " +
            "FROM post p " +
            "JOIN user u ON p.user_id = u.user_id " +
            "WHERE p.content LIKE CONCAT('%', #{keyword}, '%')")
    List<PostDTO> findByContent(@Param("keyword") String keyword);

    // 작성자로 검색 (nickname으로 검색)
    @Select("SELECT p.post_id, p.user_id, p.title, p.content, p.category, p.created_at, p.updated_at, p.view_count, u.nickname " +
            "FROM post p " +
            "JOIN user u ON p.user_id = u.user_id " +
            "WHERE u.nickname = #{nickname}")
    List<PostDTO> findByNickname(@Param("nickname") String nickname);

    // 비밀번호 확인
    @Select("SELECT password FROM post WHERE post_id = #{post_id}")
    String findPasswordById(@Param("post_id") int post_id);

    // ID로 게시글 조회
    @Select("SELECT p.post_id, p.user_id, p.title, p.content, p.category, p.created_at, p.updated_at, p.view_count, u.nickname " +
            "FROM post p " +
            "JOIN user u ON p.user_id = u.user_id " +
            "WHERE p.post_id = #{post_id}")
    PostDTO getOneById(@Param("post_id") int post_id);

}
