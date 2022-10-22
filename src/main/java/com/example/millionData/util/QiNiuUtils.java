package com.example.millionData.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.InputStream;

/**
 * 七牛云工具类
 */
@Slf4j
@Component
public class QiNiuUtils {

    @Value("${qiniu.access-key}")
    private String ACCESS_KEY;

    @Value("${qiniu.secret-key}")
    private String SECRET_KEY;

    @Value("${qiniu.bucket}")
    private String BUCKET;

    @Value("${qiniu.url}")
    private String URL;

    @Value("${qiniu.expire-seconds}")
    private long EXPIRE_SECONDS;

    /**
     * 上传文件
     * @param file
     * @param fileName
     * @return
     */
    public String upload(File file, String fileName) {

        log.info("[ 七牛云上传文件 ] 开始上传文件，线程名为：[{}]", Thread.currentThread().getName());
        StopWatch sw = new StopWatch();
        sw.start("七牛云上传文件");

        // Region.huanan() 注意替换成七牛云空间对应的存储区域
        Configuration cfg = new Configuration(Region.huanan());

        UploadManager uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String token = auth.uploadToken(BUCKET);

        // 上传文件
        try {
            uploadManager.put(file, fileName, token);
            sw.stop();
            log.info("[ 七牛云上传文件 ] 文件上传成功，线程名为：[{}]，耗时为：[{}]", Thread.currentThread().getName(), sw.getTotalTimeMillis());
        } catch (QiniuException e) {
            e.printStackTrace();
            log.error("[ 七牛云上传文件 ] 上传文件失败，线程名为：[{}]，异常信息为：[{}]", Thread.currentThread().getName(), e.getMessage());
            return null;
        }

        DownloadUrl url = new DownloadUrl(URL, false, fileName);
        // 有效期
        long deadline = System.currentTimeMillis() / 1000 + EXPIRE_SECONDS;

        // 生成私有空间的下载路径
        try {
            String downloadUrl = url.buildURL(auth, deadline);
            log.info("[ 七牛云上传文件 ] 生成私有空间的下载路径成功，线程名为：[{}]", Thread.currentThread().getName());
            return downloadUrl;
        } catch (QiniuException e) {
            e.printStackTrace();
            log.error("[ 七牛云上传文件 ] 生成私有空间的下载路径失败，线程名为：[{}]，异常信息为：[{}]", Thread.currentThread().getName(), e.getMessage());
            return null;
        }
    }
}
