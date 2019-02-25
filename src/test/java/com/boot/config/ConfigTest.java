package com.boot.config;

import com.boot.Application;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by L on 2019/2/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
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

    @Test
    public void testHistoryLevel() {
        //启动流程
        Map<String, Object> params = new HashMap<>();
        params.put("start1", "start1");
        params.put("start2", "start2");
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addClasspathResource("my-process.bpmn20.xml")
                .deploy();
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("myProcess", params);
        if (processInstance != null) {
            //修改变量
            List<Execution> executionList = processEngine.getRuntimeService().createExecutionQuery().list();
            LOGGER.info("executionList.size = {}", executionList.size());
            processEngine.getRuntimeService().setVariable(executionList.iterator().next().getId(), "start1", "start1_");
            //提交表单
            List<Task> taskList = processEngine.getTaskService().createTaskQuery().list();
            Map<String, String> formParams = new HashMap<>();
            formParams.put("form1", "form1");
            formParams.put("from2", "from2");
            taskList.forEach(task -> processEngine.getFormService().submitTaskFormData(task.getId(), formParams));
            //历史活动
            List<HistoricActivityInstance> historicActivityInstanceList = processEngine.getHistoryService().createHistoricActivityInstanceQuery().list();
            LOGGER.info("historicActivityInstanceList.size = {}", historicActivityInstanceList.size());
            //历史表单
            List<HistoricTaskInstance> historicTaskInstanceList = processEngine.getHistoryService().createHistoricTaskInstanceQuery().list();
            LOGGER.info("historicTaskInstanceList.size = {}", historicTaskInstanceList.size());
        }
    }
}
