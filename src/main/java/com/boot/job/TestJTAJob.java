package com.boot.job;

import com.boot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * Created by L on 2019/10/4.
 */
@Slf4j
@Component
public class TestJTAJob implements Job {

    @Autowired
    private UserService userService;

    @Override
    public void execute(JobExecutionContext context) {
        userService.testJta();
    }
}
