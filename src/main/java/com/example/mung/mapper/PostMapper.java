package com.example.mung.mapper;

import com.example.mung.domain.PostDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT * FROM post")
    List<PostDTO> getList(); // 게시글 전부 가져오기

    @Select("SELECT  * FROM post WHERE title = #{title}")
    PostDTO getOneByTitle(@Param("title") String title); // 타이틀명으로 가져오기

    @Update("UPDATE post SET title = #{title}, content = #{content}, " +
            "category = #{category}, updated_at = #{updated_at}, password = #{password}, files = #{files} " +
            "WHERE post_id = #{post_id}")
    boolean update(PostDTO post);

    @Insert("INSERT INTO post (user_id, title, content, category, created_at, password, files) " +
            "VALUES (#{user_id}, #{title}, #{content}, #{category}, #{created_at}, #{password}, #{files})")
    boolean insert(PostDTO post); // 유저아이디, 제목, 내용, 조회수, 등록날짜, 수정날짜, 비밀번호, 파일을 게시글로 등록

    @Delete("delete from post where post_id = #{post_id}")
    boolean delete(@Param("post_id") int post_id); // 게시글 번호로 삭제


}
