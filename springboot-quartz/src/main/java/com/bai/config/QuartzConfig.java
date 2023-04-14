package com.bai.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.bai.job.HelloJob;

/**
 * @author pdai
 */
@Configuration
public class QuartzConfig {

    @Bean("helloJob")
    public JobDetail helloJobDetail() {
        // 使用JobBuilder创建JobDetail（包含Job）
        // JobDetail：用来绑定Job，并且在job执行的时候携带一些执行的信息
        return JobBuilder.newJob(HelloJob.class)
                .withIdentity("DateTimeJob")
                .usingJobData("msg", "Hello Quartz") //jobDataMap
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }

    @Bean
    public Trigger printTimeJobTrigger() {
        // 每秒执行一次
        // 调度器，代表Quartz的一个独立运行容器，Trigger和JobDetail可以注册到Scheduler中
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
        return TriggerBuilder.newTrigger() //触发器
                .forJob(helloJobDetail()) // 执行哪一个job
                .withIdentity("quartzTaskService") // 标识
                .withSchedule(cronScheduleBuilder) // 绑定到调度器
                .build();
    }
    //一般流程：
    // 1.创建Scheduler实例
    // 2.创建JobDetail实例，并与具体的Job实现类绑定
    // 3.构建Trigger实例，使用cron表达式定义执行规则
    // 4.scheduler.scheduleJob(jodDetail, cronTrigger)
    // 5.scheduler.start()
}
