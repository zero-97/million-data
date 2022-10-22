package com.example.millionData.service;

public interface IAsyncTaskLogService {

    int insert();

    void success(int id, String url);

    void failure(int id);

    String getUrl(int id);
}
