package com.example.mung.mapper;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.AccomVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccomMapper {
    @Select("SELECT accom_id, user_id, accom_name, accom_location, accom_phone, accom_caution, accom_description, accom_images_url, accom_amenities" +
                " FROM ACCOMMODATION")
    public List<AccomDTO> getList(); //숙소 전체 리스트 불러오기

    @Select("SELECT accom_name, accom_location, accom_phone, accom_caution, accom_description, accom_images_url, accom_amenities " +
                    "FROM ACCOMMODATION WHERE accom_location like  '%${location}%'")
    public List<AccomDTO>getListByLocation(String location); //지역 기반으로 숙소 불러오기 (강원 지역, 경기지역)

    @Select("SELECT  accom_name, accom_location " +
            " FROM accommodation " +
            "WHERE user_id = #{user_id} AND accom_name = #{accom_name}")
    public List<AccomVO>getListByUserAndAccom_name(AccomVO vo);
    //한 유저가 동일한 숙소를 등록하는지 확인하기위해

    @Insert("INSERT INTO ACCOMMODATION (user_id, accom_name, accom_location, accom_phone, accom_caution, accom_description, accom_images_url, accom_amenities)" +
            "VALUE(#{user_id},#{accom_name},#{accom_location},#{accom_phone},#{accom_caution},#{accom_description},#{accom_images_url},#{accom_amenities})")
    public boolean insert(AccomVO vo); //숙소 등록

    @Delete("DELETE FROM ACCOMMODATION WHERE accom_id = #{accom_id}")
    public boolean delete(int accom_id); // 숙소 삭제

    @Update("UPDATE ACCOMMODATION SET  accom_name = #{accom_name}, accom_location=#{accom_location}, " +
            " accom_phone = #{accom_phone}, accom_caution = #{accom_caution}, " +
            " accom_description = #{accom_description}, accom_images_url = #{accom_images_url}, " +
            " accom_amenities = #{accom_amenities} " +
            " WHERE accom_id = #{accom_id}")
    public boolean update( AccomVO vo); // 숙소 수정
 /* 처음에 public boolean update(AccomVO vo, accom_id)  이렇게 accom_id를 파라미터로 받았는데
    이렇게 하면 vo객체에도 accom_id가 있고 파라미터로도 accom_id가 있어서 값을 찾지 못하게 됨
    그래서 객체만 부르고 아예 set으로 값을 모두 설정한다음  출력하니까 성공
*/
}