/*
package com.example.mung.mapper;

import com.example.mung.domain.LoginDTO;
import com.example.mung.domain.UserDTO;
import com.example.mung.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper
public interface LoginMapper {

    //USER 테이블 안에 있는 컬럼 전체
    //user_name //user_loginId //user_email //password //user_phone //user_birth //user_gender //nickname //role //user_address
    //profile_image_url //pet_info //business_number //business_sns_url


    //아이디 전체 출력하여 담기
    @Select("select user_loginId from user")
    List<String> idList();


    //아이디 비밀번호로 정보 조회
    @Select("select * from user where user_loginId =#{user_loginId} and password = #{password}")
    UserVO loginActive(LoginDTO dto);

    // User 아이디 중복 조회할 때 사용할 메서드
    @Select("select count(*) from user where user_loginId = #{user_loginId}")
    boolean loginByIdCheck(String user_loginId);

    //아이디로 존재 여부 확인 -> 이름 출력
    @Select("select user_name from user where user_loginId = #{user_loginId}")
    String nameCheck(String user_loginId);

    //일반 회원 회원가입
    @Insert("insert into user (user_name,user_loginId,user_email,password,user_phone,user_birth,user_gender,nickname,role) " +
            "values " +
            "(#{user_name},#{user_loginId},#{user_email},#{password},#{user_phone},#{user_birth},#{user_gender},#{nickname},#{role})")
    public boolean generalJoin(UserDTO dto);


    //아이디 찾기
    @Select("select user_loginId from user where user_name = #{name} and user_email = #{email} and user_birth = #{birth}")
    public String findId(String name,String email, LocalDateTime birth);

    //비번 수정 전 아이디 확인
    @Select("select user_name from user where user_loginId = #{id} and user_email = #{email} and user_birth = #{birth}")
    public String idCheckForModifyPassword(String id,String email, LocalDateTime birth);

    //비번 수정
    @Update("UPDATE user SET password=${newPassword} WHERE user_loginId = #{id}")
    int updatePassword(String id, String newPassword);







    //일반 회원 로그인
    //@Select("select * from user where user_loginId =#{user_loginId} and password=#{password}")
   // public Optional<UserVO> generalLogin(LoginDTO dto);

    //일반 회원 아이디 찾기
//    @Select("")
//    public String generalFindId();

    //사업자 회원 회원가입
//    @Insert("insert into user (user_name,user_loginId,user_email,password,user_phone,user_birth,user_gender,nickname,role,user_address" +
//            ",business_number,business_sns_url) " +
//            "values " +
//            "(#{user_name},#{user_loginId},#{user_email},#{password},#{user_phone},#{user_birth},#{user_gender},#{nickname},#{role},#{user_address}" +
//            ",#{business_number},#{business_sns_url})")
//    public boolean businessJoin(UserDTO dto);

    //사업자 회원 로그인
   // @Select("")
  //  public Optional<UserVO> businessLogin();

    //사업자 회원 아이디 찾기
//    @Select("")
//    public String businessFindId();
//
//    //비밀번호 수정
//    @Update("")
//    public boolean passwordChange();

//    //아이디로 모든 아이디 정보 조회
//    @Select("select * from user where user_loginId = #{user_loginId}")
//    Optional<UserVO> findIdAll(String user_loginId);

}
*/
