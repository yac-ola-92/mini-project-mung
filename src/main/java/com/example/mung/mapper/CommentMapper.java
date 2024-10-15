package com.example.mung.mapper;

import com.example.mung.domain.CommentDTO;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("SELECT  * FROM comment")
    List<CommentDTO> getList();

    //유저아이디로 빼오기
    @Select("SELECT * FROM comment WHERE user_id =#{user_id}")
    List<CommentDTO> getListByUserId(@Param("user_id")String user_id);

    @Insert("INSERT  INTO comment (comment_id, post_id, content, created_at, user_id) "+
    "VALUES (#{comment_id}, #{post_id}, #{content}, #{created_at}, #{user_id})")
    boolean insert(CommentDTO comment);

    @Update("UPDATE comment SET contetn=#{content}, " +
            "created_at=#{created_at} WHERE user_id=#{user_id}")
    boolean update(CommentDTO comment);

    @Delete("DELETE FROM comment WHERE comment_id=#{comment_id}")
    boolean delete(@Param("comment_id")String comment_id);

}
