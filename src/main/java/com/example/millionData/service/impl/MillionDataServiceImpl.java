package com.example.millionData.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.millionData.entity.MillionData;
import com.example.millionData.mapper.MillionDataMapper;
import com.example.millionData.service.IAsyncTaskLogService;
import com.example.millionData.service.IMillionDataService;
import com.example.millionData.util.QiNiuUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Slf4j
@Service
public class MillionDataServiceImpl extends ServiceImpl<MillionDataMapper, MillionData> implements IMillionDataService {

    @Value("${data.page-size}")
    private int PAGE_SIZE;

    @Value("${excel.sheet.max-size}")
    private int MAX_SIZE;

    @Value("${file.path}")
    private String FILE_PATH;

    @Autowired
    private QiNiuUtils qiNiuUtils;

    @Autowired
    private IAsyncTaskLogService asyncTaskLogService;

    @Override
    public void export(HttpServletResponse response) throws IOException {
        doExport(response.getOutputStream());
    }

    @Async("excelExportExecutor")
    @Override
    public void exportAsyn(int logId) throws FileNotFoundException {
        String fileName = logId+".xlsx";
        String filePath = FILE_PATH+fileName;

        log.info("[ 异步导出 ] 开始任务，线程名为：[{}]，文件名为：[{}]", Thread.currentThread().getName(), fileName);
        StopWatch sw = new StopWatch();
        sw.start("异步导出");

        // 本地临时文件
        doExport(new FileOutputStream(filePath));

        // 上传七牛云
        File file = new File(filePath);
        String url = qiNiuUtils.upload(file, fileName);

        // 更新日志状态
        if(url == null) {
            asyncTaskLogService.failure(logId);
        } else {
            asyncTaskLogService.success(logId, url);
            // 删除本地临时文件
            file.delete();
        }

        sw.stop();
        log.info("[ 异步导出 ] 结束任务，耗时为：[{}]", Thread.currentThread().getName(), sw.getTotalTimeMillis());

    }

    public void doExport(OutputStream out) {
        log.info("[ excel 导出文件 ] 开始导出文件，线程名为：[{}]", Thread.currentThread().getName());
        StopWatch sw = new StopWatch();
        sw.start("excel 导出文件");

        int sheetCount = 1;

        ExcelWriter excelWriter = new ExcelWriterBuilder()
                .excelType(ExcelTypeEnum.XLSX)
                .file(out)
                .head(MillionData.class)
                .build();
        WriteSheet writeSheet = null;

        int current = 0;
        for(;;current++) {
            List<MillionData> list = baseMapper.searchByIndex(PAGE_SIZE, current*PAGE_SIZE);

            // 每个 sheet 页最多 100w 数据，因为 excel 限制最多 1048576 行
            if(current*PAGE_SIZE % MAX_SIZE == 0) {
                writeSheet = EasyExcel.writerSheet("sheet"+sheetCount).build();
                sheetCount++;
            }

            if(list.isEmpty()) {
                break;
            }
            excelWriter.write(list, writeSheet);
        }
        excelWriter.finish();

        sw.stop();
        log.info("[ excel 导出文件 ] 导出文件成功，线程名为：[{}]，耗时：[{}]", Thread.currentThread().getName(), sw.getTotalTimeMillis());
    }
}
