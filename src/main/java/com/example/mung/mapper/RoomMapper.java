package com.example.mung.mapper;

import com.example.mung.domain.RoomDTO;
import com.example.mung.domain.RoomVO;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface RoomMapper {

    @Select("SELECT * FROM ROOM WHERE accom_id = ${accom_id}")
    public List<RoomDTO>getListByAccom_id(int accom_id); // 숙소id로 해당 숙소의 객실 전부 출력

    @Select("SELECT room_name, room_type, room_price, room_images_url, room_info, room_amount, pet_kind" +
            "FROM ROOM " +
            "WHERE pet_kind = #{pet_kind}")
    public List<RoomDTO>getListByPet_kind(String pet_kind);

    @Insert("INSERT INTO ROOM (accom_id, room_name, room_type, room_price, room_images_url, room_info, room_amount, pet_kind)" +
            "VALUES (${accom_id}, #{room_name}, #{room_type}, #{room_price},#{room_images_url}, #{room_info}, ${room_amount}, #{pet_kind});")
    public boolean insert(RoomVO room); //객실 추가

    @Update("UPDATE ROOM SET room_name = #{room_name}, room_type =#{room_type}, room_price =#{room_price}, room_images_url =#{room_images_url}" +
                      " , room_info = #{room_info} , room_amount = #{room_amount}, pet_kind =#{pet_kind}" +
                     "  WHERE room_id = #{room_id}")
    public boolean update(RoomVO vo);

    @Delete("DELETE FROM ROOM WHERE room_id = ${room_id}")
    public boolean delete(int room_id);
}
