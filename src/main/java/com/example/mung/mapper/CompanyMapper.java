package com.example.mung.mapper;

import com.example.mung.domain.CompanyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CompanyMapper {

    @Select("select * from company")
    CompanyVO getOneCompany();
}
