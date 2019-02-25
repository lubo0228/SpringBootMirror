package com.boot.config;

import com.boot.component.MDCCommandInvoker;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.history.HistoryLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        configuration.setCommandInvoker(commandInvoker);
        configuration.setActivityFontName("宋体");
        configuration.setLabelFontName("宋体");
//        configuration.setHistory(HistoryLevel.NONE.getKey());
//        configuration.setHistory(HistoryLevel.ACTIVITY.getKey());
        configuration.setHistory(HistoryLevel.AUDIT.getKey());
        return configuration;
    }
}
