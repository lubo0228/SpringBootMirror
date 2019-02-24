package com.boot.config;

import com.boot.Application;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by L on 2019/2/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
public class ConfigTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigTest.class);

    @Autowired
    @Qualifier("processEngineConfiguration")
    private ProcessEngineConfiguration processEngineConfiguration;

    @Test
    public void testConfig() {
//        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        LOGGER.info("configuration = {}", configuration);
    }

    @Test
    public void testMDC() {
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addClasspathResource("my-process.bpmn20.xml")
                .deploy();
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("myProcess");
        if (processInstance != null) {
            Task task = processEngine.getTaskService().createTaskQuery().singleResult();
            if (task != null) {
                processEngine.getTaskService().complete(task.getId());
            }
        }

    }
}
