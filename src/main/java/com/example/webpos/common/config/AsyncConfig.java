package com.example.webpos.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig {
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // thread-pool에 항상 살아있는 thread 최소 개수
        executor.setMaxPoolSize(5); // thread-pool에서 사용 가능한 최대 thread 개수
        executor.setQueueCapacity(500); // thread-pool에서 사용할 최대 queue 크기
        executor.setThreadNamePrefix("Web-Pos");
        executor.initialize();
        return executor;
    }
}
