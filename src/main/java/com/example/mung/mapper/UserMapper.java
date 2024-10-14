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
    @Select("select * from user")
    public List<UserDTO> listAll();

    // User 상세 조회
    @Select("select * from user where ${id}")
    public UserDTO getOne(int id);

    // User 등록
    @Insert("INSERT INTO user (user_name, user_email, password, user_phone, user_birth, user_gender, nickname, role, user_address, profile_image_url, pet_info, business_number, business_sns_url) " +
            "VALUES (#{user_name}, #{user_email}, #{password}, #{user_phone}, #{user_birth}, #{user_gender}, #{nickname}, #{role}, #{user_address}, #{profile_image_url}, #{pet_info}, #{business_number}, #{business_sns_url})")
    public void insert(UserVO vo);

    // User 수정
    @Update("update user set user_name =#{user_name},user_email=${user_email},password=${password},user_phone=#{user_phone},user_birth=#{user_birth},user_gender=#{user_gender}" +
            "nickname=#{nickname},role=#{role},user_address=#{user_address},profile_image_url=#{profile_image_url},pet_info=#{pet_info},business_number=#{business_number},business_sns_url=#{business_sns_url}" +
            "where user_id = ${user_id}")
    public void update(int user_id);
    // User 삭제

}
