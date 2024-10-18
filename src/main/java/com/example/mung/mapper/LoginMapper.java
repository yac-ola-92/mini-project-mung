/*
package com.example.mung.mapper;

import com.example.mung.domain.LoginDTO;
import com.example.mung.domain.UserDTO;
import com.example.mung.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Optional;

@Mapper
public interface LoginMapper {


//    //아이디 비밀번호로 정보 조회
//    @Select("select * from user where user_loginId =#{user_loginId} and password = #{password}")
//    UserVO loginActive(LoginDTO dto);
//
//    // User 아이디 조회
//    @Select("select * from user where user_loginId = #{user_loginId}")
//    UserVO loginIdCheck(String user_loginId);
//
//    //아이디로 아이디 존재 여부 확인 -> 이름 출력
//    @Select("select user_name from user where user_loginId = #{user_loginId}")
//    String findId(String user_loginId);
//
//    //아이디로 모든 아이디 정보 조회
//    @Select("select * from user where user_loginId = #{user_loginId}")
//    Optional<UserVO> findIdAll(String user_loginId);

    //USER
    //user_name //user_loginId //user_email //password //user_phone //user_birth //user_gender //nickname //role //user_address
    //profile_image_url //pet_info //business_number //business_sns_url
    //일반 회원 회원가입
    @Insert("insert into user (user_name,user_loginId,user_email,password,user_phone,user_birth,user_gender,nickname,role,user_address) " +
            "values " +
            "(#{user_name},#{user_loginId},#{user_email},#{password},#{user_phone},#{user_birth},#{user_gender},#{nickname},#{role},#{user_address})")
    public boolean generalJoin(UserDTO dto);

    //일반 회원 로그인
    @Select("select * from user where user_loginId =#{user_loginId} and password=#{password}")
    public Optional<UserVO> generalLogin(LoginDTO dto);

    //일반 회원 아이디 찾기
    @Select("")
    public String generalFindId();

    //사업자 회원 회원가입
    @Insert("insert into user (user_name,user_loginId,user_email,password,user_phone,user_birth,user_gender,nickname,role,user_address" +
            ",business_number,business_sns_url) " +
            "values " +
            "(#{user_name},#{user_loginId},#{user_email},#{password},#{user_phone},#{user_birth},#{user_gender},#{nickname},#{role},#{user_address}" +
            ",#{business_number},#{business_sns_url})")
    public boolean businessJoin(UserDTO dto);

    //사업자 회원 로그인
    @Select("")
    public Optional<UserVO> businessLogin();

    //사업자 회원 아이디 찾기
    @Select("")
    public String businessFindId();

    //비밀번호 수정
    @Update("")
    public boolean passwordChange();


}
*/
