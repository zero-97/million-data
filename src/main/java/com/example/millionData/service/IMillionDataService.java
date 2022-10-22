package com.example.millionData.service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IMillionDataService {

    void export(HttpServletResponse response) throws IOException;

    void exportAsyn(int uuid) throws FileNotFoundException;
}
