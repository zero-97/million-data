package com.example.millionData.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.millionData.common.LogStatusEnum;
import com.example.millionData.entity.AsyncTaskLog;
import com.example.millionData.mapper.AsyncTaskLogMapper;
import com.example.millionData.service.IAsyncTaskLogService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AsyncTaskLogServiceImpl extends ServiceImpl<AsyncTaskLogMapper, AsyncTaskLog> implements IAsyncTaskLogService {

    @Override
    public int insert() {
        AsyncTaskLog entity = new AsyncTaskLog();
        entity.setCreatetime(new Date());
        entity.setStatus(LogStatusEnum.WAITING.getStatus());
        baseMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void success(int id, String url) {
        AsyncTaskLog entity = new AsyncTaskLog();
        entity.setId(id);
        entity.setUrl(url);
        entity.setStatus(LogStatusEnum.SUCCESS.getStatus());
        baseMapper.updateById(entity);
    }

    @Override
    public void failure(int id) {
        AsyncTaskLog entity = new AsyncTaskLog();
        entity.setId(id);
        entity.setStatus(LogStatusEnum.FAILURE.getStatus());
        baseMapper.updateById(entity);
    }

    @Override
    public String getUrl(int id) {
        return baseMapper.getUrl(id);
    }
}
