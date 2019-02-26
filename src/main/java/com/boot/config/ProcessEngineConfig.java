package com.boot.config;

import com.boot.interceptor.MDCCommandInvoker;
import com.boot.listener.JobEventListener;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.impl.asyncexecutor.DefaultAsyncJobExecutor;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by L on 2019/2/24.
 */
@Configuration
public class ProcessEngineConfig {

    @Autowired
    private MDCCommandInvoker commandInvoker;

    @Bean(name = "commandInvoker")
    public MDCCommandInvoker getCommandInvoker() {
        return commandInvoker;
    }

    @Bean(name = "processEngineConfiguration")
    public ProcessEngineConfiguration setProcessEngineConfiguration(@Qualifier("commandInvoker") MDCCommandInvoker commandInvoker) {
        ProcessEngineConfigurationImpl configuration = new StandaloneProcessEngineConfiguration();
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf-8");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("root");
//        configuration.setCommandInvoker(commandInvoker);
        configuration.setActivityFontName("宋体");
        configuration.setLabelFontName("宋体");
//        configuration.setHistory(HistoryLevel.NONE.getKey());
//        configuration.setHistory(HistoryLevel.ACTIVITY.getKey());
//        configuration.setHistory(HistoryLevel.AUDIT.getKey());
//        configuration.setEnableDatabaseEventLogging(true);
        //监听器
        List<ActivitiEventListener> eventListeners = new LinkedList<>();
//        eventListeners.add(new ProcessEventListener());
//        eventListeners.add(new CustomEventListener());
        eventListeners.add(new JobEventListener());
        configuration.setEventListeners(eventListeners);
//        Map<String, List<ActivitiEventListener>> typedListeners = new HashMap<>();
//        List<ActivitiEventListener> eventListeners = new LinkedList<>();
//        eventListeners.add(new ProcessEventListener());
//        typedListeners.put(ActivitiEventType.ACTIVITY_STARTED.name(), eventListeners);
//        configuration.setTypedEventListeners(typedListeners);
        //拦截器
//        List<CommandInterceptor> customPreCommandInterceptors = new LinkedList<>();
//        customPreCommandInterceptors.add(new DurationCommandInteceptor());
//        configuration.setCustomPreCommandInterceptors(customPreCommandInterceptors);
        //执行器
        configuration.setAsyncExecutorActivate(true);
        DefaultAsyncJobExecutor asyncExecutor = new DefaultAsyncJobExecutor();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 100L,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());
        asyncExecutor.setExecutorService(threadPoolExecutor);
        configuration.setAsyncExecutor(asyncExecutor);
        return configuration;
    }
}
