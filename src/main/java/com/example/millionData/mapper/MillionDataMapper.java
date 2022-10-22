package com.example.millionData.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.millionData.entity.MillionData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MillionDataMapper extends BaseMapper<MillionData> {

    List<MillionData> searchByIndex(@Param("pageSize") int pageSize, @Param("position") int position);

}
