package com.bai.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableAsync // 开启异步任务
@EnableScheduling
public class HelloSchedule {
    // 秒 分 时 日 月 周
    @Scheduled(cron = "* * * * * ?")
    public void hello() throws Exception {
        log.info(String.valueOf(Thread.currentThread().getId()));
        log.info("hello");
    }

    @Async // 异步执行
    @Scheduled(cron = "0/3 * * * * ?")
    public void hello2() {
        log.info(String.valueOf(Thread.currentThread().getId()));
        log.info("hello2..");
    }
}
