package com.example.mung.mapper;

import com.example.mung.domain.UserDTO;
import com.example.mung.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    // User 전체 조회
    @Select("select * from user where user_id > 0 ")
    List<UserDTO> getList();

    // User 상세 조회
    @Select("select * from user where user_id = ${id}")
    UserDTO getOne(int id);

    // User 등록
    @Insert("INSERT INTO user (user_name,user_loginId, user_email, password, user_phone, user_birth, user_gender, nickname, role, user_address, profile_image_url, pet_info, business_number, business_sns_url) " +
            "VALUES (#{user_name},#{user_loginId}, #{user_email}, #{password}, #{user_phone}, #{user_birth}, #{user_gender}, #{nickname}, #{role}, #{user_address}, #{profile_image_url}, #{pet_info}, #{business_number}, #{business_sns_url})")
    int insert(UserVO vo);

    // Host 등록 만들어야함

    // User 수정
    @Update("UPDATE user SET user_name = #{user_name}, user_email = #{user_email}, password = #{password}, " +
            "user_phone = #{user_phone}, user_birth = #{user_birth}, user_gender = #{user_gender}, " +
            "nickname = #{nickname}, role = #{role}, user_address = #{user_address}, " +
            "profile_image_url = #{profile_image_url}, pet_info = #{pet_info} " +
            "WHERE user_id = #{user_id}")
    void update(UserVO vo);

    // User 삭제

}
