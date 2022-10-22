package com.example.millionData.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.millionData.entity.AsyncTaskLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AsyncTaskLogMapper extends BaseMapper<AsyncTaskLog> {

    String getUrl(@Param("id") int id);
}
