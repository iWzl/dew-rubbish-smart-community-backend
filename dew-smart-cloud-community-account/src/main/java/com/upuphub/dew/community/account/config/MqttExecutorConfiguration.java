package com.upuphub.dew.community.account.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * MQTT监听异步处理的线程此配置
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/13 18:08
 */


@Configuration
@EnableAsync
@Slf4j
public class MqttExecutorConfiguration {

    /**
     * 方法名只要在项目中唯一性，可以适当任意取（最好遵循一定的规则）
     */
    @Bean
    public Executor mqttHandlerExecutor() {
        log.info("start mqttHandlerExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(2);
        //配置最大线程数
        executor.setMaxPoolSize(5);
        //配置队列大小
        executor.setQueueCapacity(99999);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("MQTT-HANDLER-");
         // rejection-policy：当pool已经达到max size的时候，如何处理新任务
         // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化执行器
        executor.initialize();
        return executor;
    }
}
