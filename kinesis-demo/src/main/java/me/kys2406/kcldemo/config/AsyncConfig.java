package me.kys2406.kcldemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableAsync
@RequiredArgsConstructor
public class AsyncConfig {
    private final Environment env;

    @Bean(name = "consumerExecutor")
    public ThreadPoolTaskExecutor consumerExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(8);
        taskExecutor.setMaxPoolSize(8);
        taskExecutor.setQueueCapacity(16);
        taskExecutor.setThreadNamePrefix("Consumer-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean(name = "producerScheduler")
    public ThreadPoolTaskScheduler producerScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(8);
        scheduler.initialize();
        return scheduler;
    }
}
