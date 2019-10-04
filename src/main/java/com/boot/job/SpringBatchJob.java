package com.boot.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by L on 2019/10/4.
 */
@Slf4j
@Component
public class SpringBatchJob implements org.quartz.Job {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    @Qualifier("dataHandleJob")
    private Job job;

    @Override
    public void execute(JobExecutionContext context) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("date", new Date())
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
