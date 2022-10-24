package com.example.millionData.controller;

import com.example.millionData.service.IAsyncTaskLogService;
import com.example.millionData.service.IMillionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/milloinData")
public class MillionDataController {

    @Autowired
    private IMillionDataService service;

    @Autowired
    private IAsyncTaskLogService asyncTaskLogService;

    /**
     * 异步-分页导出
     * @return
     * @throws FileNotFoundException
     */
    @GetMapping("exportByPage")
    public int exportByPage() {
        int logId = asyncTaskLogService.insert();
        service.exportByPage(logId);
        return logId;
    }

    /**
     * 异步-流式导出
     * @return
     * @throws FileNotFoundException
     */
    @GetMapping("exportByStream")
    public int exportByStream() {
        int logId = asyncTaskLogService.insert();
        service.exportByStream(logId);
        return logId;
    }

    /**
     * 获取下载路径
     * @param id 日志表ID
     * @return
     */
    @GetMapping("getUrl")
    public String getUrl(@RequestParam int id) {
        return asyncTaskLogService.getUrl(id);
    }
}
