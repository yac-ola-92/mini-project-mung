package com.example.mung.mapper;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.AccomVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccomMapper {
    @Select("SELECT * FROM ACCOMMODATION")
    public List<AccomDTO> getList(); //숙소 전체 리스트 불러오기

    @Select("SELECT * FROM ACCOMMODATION WHERE accom_location like  '%${key}%'")
    public List<AccomDTO>getListByLocation(String key); //지역 기반으로 숙소 불러오기 (강원 지역, 경기지역)

    @Insert("INSERT INTO ACCOMMODATION (user_id, accom_name, accom_location, accom_phone, accom_caution, accom_description, accom_images_url, accom_amenities)" +
            "VALUE(${user_id},#{accom_name},#{accom_location},#{accom_phone},#{accom_caution},#{accom_description},#{accom_images_url},#{accom_amenities})")
    public boolean insert(AccomVO accom); //숙소 등록

    @Delete("DELETE FROM ACCOMMODATION WHERE accom_id = ${accom_id}")
    public boolean delete(int accom_id); // 숙소 삭제

    @Update("UPDATE ACCOMMODATION SET  accom_name = #{accom_name}, accom_location=#{accom_location}, " +
            " accom_phone = #{accom_phone}, accom_caution = #{accom_caution}, " +
            " accom_description = #{accom_description}, accom_images_url = #{accom_images_url}, " +
            " accom_amenities = #{accom_amenities} " +
            " WHERE accom_id = ${accom_id}")
    public boolean update(@Param("accom_id") int accom_id, @Param("accom") AccomVO accom); // 숙소 수정


}