package com.example.mung.mapper;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.AccomVO;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface AccomMapper {
    @Select("SELECT * FROM ACCOMMODATION")
    public List<AccomDTO>getList();

    // filtering : accom_location, capacity_standard , capacity_max, pet_kind

    @Select("SELECT a.accom_name, a.accom_location, a.accom_images_url, r.room_price, r.pet_kind, re.rating " +
            "FROM ACCOMMODATION a JOIN ROOM r ON a.accom_id = r.accom_id " +
            "LEFT JOIN REVIEW re ON a.user_id = re.user_id " +
            "ORDER BY re.rating DESC")
   public List<AccomDTO> getListByRating();
    // 메인페이지에 별점 높은 숙소들을 표시하기 위함
    //public Page<AccomDTO> getListByRating();

    @Select("SELECT a.accom_name, a.accom_location, a.accom_images_url, re.rating, r.room_price, r.pet_kind " +
            "FROM ACCOMMODATION a " +
            "INNER JOIN ROOM r ON a.accom_id = r.accom_id " +
            "LEFT JOIN REVIEW re ON a.user_id = re.user_id" +
            " WHERE a.accom_location LIKE CONCAT('%', #{location}, '%') " +
            "AND #{capacity} BETWEEN r.capacity_standard AND r.capacity_max;")
    public List<AccomDTO> getListByLocation(@Param("location") String location, @Param("capacity") int capacity);

    //지역 기반으로 숙소 불러오기 (강원 지역, 경기지역) room tb 와 join 해서 인원수도 비교하여 숙소출력

    @Select("SELECT  accom_name, accom_location " +
            " FROM accommodation " +
            "WHERE user_id = #{user_id} AND accom_name = #{accom_name}")
    public List<AccomVO>getoneByUserAndAccom_name(AccomVO vo);
    //한 유저가 동일한 숙소를 등록하는지 확인하기위해

    @Select("SELECT a.accom_id, a.accom_location, a.accom_phone, a.accom_caution, a.accom_description, a.accom_images_url, a.accom_amenities," +
            "r.rating, r.comment, u.business_number, u.business_sns_url, u.nickname" +
            "FROM ACCOMMODATION a JOIN REVIEW r ON a.user_id = r.user_id " +
            "LEFT JOIN USER u ON a.user_id = u.user_id" +
            "WHERE accom_id = #{accom_id}")
    public AccomDTO getOneByAccom_id(int accom_id);
    //원하는 숙소를 클릭 했을 때 숙소의 상세 페이지에서 출력하기 위함

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