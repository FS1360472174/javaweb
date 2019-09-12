/*
 * AsyncConfig.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.web.proxy;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.Role;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author fangzhang
 *
 */
@Configuration
@EnableAsync(mode = AdviceMode.ASPECTJ)
@EnableLoadTimeWeaving()
public class AsyncConfig {

    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("solution-executor");
        executor.setMaxPoolSize(20);
        return executor;
    }

    // ASPECTJ 模式下需要定义
    /**
    @Bean(name="org.springframework.context.annotation.internalAsyncAnnotationProcessor")
    @Role(value=2)
    public AsyncAnnotationBeanPostProcessor asyncAdvisor() {
        return new AsyncAnnotationBeanPostProcessor();
    }
    **/
}
