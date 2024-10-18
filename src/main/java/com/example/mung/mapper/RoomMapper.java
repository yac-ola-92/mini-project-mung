package com.example.mung.mapper;

import com.example.mung.domain.RoomDTO;
import com.example.mung.domain.RoomVO;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface RoomMapper {

    @Select("<script>SELECT * FROM ROOM" +
            " <where> " +
            "<if test='pet_kind != null'> pet_kind = #{pet_kind}</if>" + //반려견의 종 필터링 이용할 경우 적용
            "<if test='room_price > 0'> and  room_price < #{room_price}</if>" +
            "</where></script>")
    public List<RoomDTO>getList();
//객실 리스트를 뽑을 건데
//처음은 수용인원 capacity_standard and capacity_max 인원을 받는 명은{guestCount}?
//두번째는 반려견의 종
//세번째는 금액 ?
  /*  @Select("SELECT room_id, accom_id, room_name, room_type, room_price, room_images_url, " +
                " room_info, room_amount, pet_kind, capacity_standard, capacity_max" +
                " FROM ROOM")
    public List<RoomDTO>getList();*/

    @Select("SELECT room_id, room_name, room_type, room_price, room_images_url," +
                    " room_info, room_amount, pet_kind, capacity_standard, capacity_max " +
            " FROM ROOM WHERE accom_id = ${accom_id}")
    public List<RoomDTO>getListByAccom_id(int accom_id); // 숙소id로 해당 숙소의 객실 전부 출력

    @Select("SELECT room_name, room_type, room_price, room_images_url, room_info, room_amount, pet_kind, capacity_standard, capacity_max" +
            " FROM ROOM WHERE pet_kind = #{pet_kind}")
    public List<RoomDTO>getListByPet_kind(String pet_kind); //반려견의 크기(소,중,대)로 객실 추출

    @Insert("INSERT INTO ROOM (accom_id, room_name, room_type, room_price, room_images_url, room_info, room_amount, pet_kind, capacity_standard, capacity_max)" +
            "VALUES (#{accom_id}, #{room_name}, #{room_type}, #{room_price},#{room_images_url}, #{room_info}, #{room_amount}, #{pet_kind}, #{capacity_standard}, #{capacity_max});")
    public boolean insert(RoomVO vo); //객실 추가

    @Update("UPDATE ROOM SET room_name = #{room_name}, room_type =#{room_type}, room_price =#{room_price}, room_images_url =#{room_images_url}" +
                      " , room_info = #{room_info} , room_amount = #{room_amount}, pet_kind =#{pet_kind}, capacity_standard =#{capacity_standard}, capacity_max =#{capacity_max}" +
                     "  WHERE room_id = #{room_id}")
    public boolean update(RoomVO vo); // 객실 수정

    @Delete("DELETE FROM ROOM WHERE room_id = #{room_id}")
    public boolean delete(int room_id); //객실 삭제
}
