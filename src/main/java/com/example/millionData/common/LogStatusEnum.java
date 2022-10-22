package com.example.millionData.common;

/**
 * 日志状态枚举类
 */
public enum LogStatusEnum {

    WAITING(1, "上传中"),
    SUCCESS(2, "上传成功"),
    FAILURE(3, "上传失败");

    private int status;

    private String name;

    LogStatusEnum(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
