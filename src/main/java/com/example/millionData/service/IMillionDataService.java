package com.example.millionData.service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IMillionDataService {

    void exportByPage(int uuid);

    void exportByStream(int uuid);

}
