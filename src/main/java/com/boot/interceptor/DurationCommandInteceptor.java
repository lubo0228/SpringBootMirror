package com.boot.interceptor;

import org.activiti.engine.impl.interceptor.AbstractCommandInterceptor;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by L on 2019/2/26.
 */
public class DurationCommandInteceptor extends AbstractCommandInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DurationCommandInteceptor.class);

    @Override
    public <T> T execute(CommandConfig commandConfig, Command<T> command) {
        long start = System.currentTimeMillis();
        try {
            return super.getNext().execute(commandConfig, command);
        } finally {
            LOGGER.info("{} 执行时间 = {}ms", command.getClass().getSimpleName(), System.currentTimeMillis() - start);
        }
    }
}
