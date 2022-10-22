package com.example.millionData.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 */
@Configuration
@EnableAsync
public class TaskExecutorConfig {

    @Value("${async.thread.core-pool-size}")
    private int corePoolSize;
    @Value("${async.thread.max-pool-size}")
    private int maxPoolSize;
    @Value("${async.thread.queue-capacity}")
    private int queueCapacity;
    @Value("${async.thread.keep-alive-seconds}")
    private int keepAliveSeconds;
    @Value("${async.thread.thread-name-prefix}")
    private String namePrefix;

    @Bean("excelExportExecutor")
    public ThreadPoolTaskExecutor excelExportExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 缓冲队列大小
        executor.setQueueCapacity(queueCapacity);
        // 线程最大空闲时间
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程名字前缀
        executor.setThreadNamePrefix(namePrefix);
        // 拒绝策略：直接拒绝
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }

}
