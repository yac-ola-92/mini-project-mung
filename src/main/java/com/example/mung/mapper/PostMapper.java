package com.example.mung.mapper;

import com.example.mung.domain.PostDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {

    // 모든 게시글 조회
    @Select("SELECT * FROM post")
    List<PostDTO> getList();

    // 페이징 처리된 게시글 조회
    @Select("SELECT * FROM post LIMIT #{size} OFFSET #{offset}")
    List<PostDTO> getPagedPost(@Param("size") int size, @Param("offset") int offset);

    // 특정 제목으로 게시글 조회
    @Select("SELECT * FROM post WHERE title = #{title}")
    PostDTO getOneByTitle(@Param("title") String title);

    // 카테고리별 게시글 조회
    @Select("SELECT * FROM post WHERE category = #{category}")
    List<PostDTO> getPostByCategory(@Param("category") PostDTO.Category category); // Category 타입으로 수정

    // 게시글 수정
    @Update("UPDATE post SET title = #{title}, content = #{content}, category = #{category}, " +
            "updated_at = #{updated_at}, password = #{password}, files = #{files} WHERE post_id = #{post_id}")
    boolean update(PostDTO post);

    // 게시글 등록
    @Insert("INSERT INTO post (user_id, title, content, category, created_at, password, files) " +
            "VALUES (#{user_id}, #{title}, #{content}, #{category}, #{created_at}, #{password}, #{files})")
    boolean insert(PostDTO post);

    // 게시글 삭제
    @Delete("DELETE FROM post WHERE post_id = #{post_id}")
    boolean delete(@Param("post_id") int post_id);

    // 조회수 증가
    @Update("UPDATE post SET view_count = view_count + 1 WHERE post_id = #{post_id}")
    boolean increaseViewCount(@Param("post_id") int post_id);

    // 게시글 검색 (타입별 검색)
    @Select("<script>" +
            "SELECT * FROM post WHERE " +
            "<if test='type.equals(\"title\")'> title LIKE CONCAT('%', #{keyword}, '%') </if>" +
            "<if test='type.equals(\"content\")'> content LIKE CONCAT('%', #{keyword}, '%') </if>" +
            "<if test='type.equals(\"user_id\")'> user_id = #{keyword} </if>" +
            "</script>")
    List<PostDTO> searchPost(@Param("keyword") String keyword, @Param("type") String type);

    // 비밀번호 확인
    @Select("SELECT password FROM post WHERE post_id = #{post_id}")
    String findPasswordById(@Param("post_id") int post_id);
}
