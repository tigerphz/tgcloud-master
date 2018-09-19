package com.tiger.tgcloud.core.config;

import com.tiger.tgcloud.base.properties.TgcloudProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 22:02
 * @version: V1.0
 * @modified by:
 */
@Configuration
@EnableAsync
@EnableScheduling
public class AsyncTaskExecutorConfiguration implements AsyncConfigurer {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private TgcloudProperties tgcloudProperties;

    @Override
    @Bean(name = "taskExecutor")
    public Executor getAsyncExecutor() {
        log.debug("Creating Async Task Executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(tgcloudProperties.getTask().getCorePoolSize());
        executor.setMaxPoolSize(tgcloudProperties.getTask().getMaxPoolSize());
        executor.setQueueCapacity(tgcloudProperties.getTask().getQueueCapacity());
        executor.setKeepAliveSeconds(tgcloudProperties.getTask().getKeepAliveSeconds());
        executor.setThreadNamePrefix(tgcloudProperties.getTask().getThreadNamePrefix());
        return new ExceptionHandlingAsyncTaskExecutor(executor);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
