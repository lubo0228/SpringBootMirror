package com.boot.config;

import com.boot.job.SpringBatchJob;
import com.boot.job.TestJTAJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by L on 2019/10/4.
 */
@Configuration
public class JobConfig {

    @Autowired
    @Qualifier("scheduler")
    private Scheduler scheduler;

    @PostConstruct
    public void postConstruct() throws Exception{
//        scheduler.scheduleJob(testJTAJobDetail(), testJTAJobTrigger());
        scheduler.scheduleJob(springBatchJobDetail(), springBatchTrigger());
    }

    private JobDetail testJTAJobDetail() {
        return JobBuilder.newJob(TestJTAJob.class)//PrintTimeJob我们的业务类
                .withIdentity("TestJTAJob")//可以给该JobDetail起一个id
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                .usingJobData("test", "testJTA")//关联键值对
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }

    private Trigger testJTAJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(testJTAJobDetail())//关联上述的JobDetail
                .withIdentity("testJTAJobTrigger")//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }

    private JobDetail springBatchJobDetail() {
        return JobBuilder.newJob(SpringBatchJob.class)//PrintTimeJob我们的业务类
                .withIdentity("SpringBatchJob")//可以给该JobDetail起一个id
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                .usingJobData("msg", "SpringBatchJob")//关联键值对
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }

    private Trigger springBatchTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(springBatchJobDetail())//关联上述的JobDetail
                .withIdentity("springBatchTrigger")//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }

}
