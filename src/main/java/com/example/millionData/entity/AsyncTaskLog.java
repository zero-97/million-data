package com.example.millionData.entity;

import lombok.Data;

import java.util.Date;

/**
 * 异步导出日志
 */
@Data
public class AsyncTaskLog {

    private Integer id;

    private Date createtime;

    private Integer status;

    private String url;

}
