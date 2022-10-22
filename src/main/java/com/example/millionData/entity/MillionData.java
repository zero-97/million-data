package com.example.millionData.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 百万数据类
 */
@Data
public class MillionData {

    @ExcelProperty(value = "id")
    private Integer id;

    @ExcelProperty(value = "uuid")
    private String uuid;

    @ExcelProperty(value = "key1")
    private String key1;

    @ExcelProperty(value = "key2")
    private String key2;

    @ExcelProperty(value = "key3")
    private String key3;
}
