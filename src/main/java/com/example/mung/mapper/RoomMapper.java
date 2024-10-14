package com.example.mung.mapper;

import com.example.mung.domain.RoomDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
public interface RoomMapper {
    @Select("SELECT * FROM ROOM WHERE accom_id = ${accom_id}")
    public List<RoomDTO>getListByAccom_id(int accom_id);
}
