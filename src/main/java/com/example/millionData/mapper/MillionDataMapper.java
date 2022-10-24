package com.example.millionData.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.millionData.entity.MillionData;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

@Mapper
public interface MillionDataMapper extends BaseMapper<MillionData> {

    List<MillionData> searchByIndex(@Param("pageSize") int pageSize, @Param("position") int position);

    void searchByStream(ResultHandler<MillionData> handler);
}
