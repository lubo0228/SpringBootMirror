package com.boot.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by L on 2019/2/24.
 */
public class MDCErrorDelegate implements JavaDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) {
        LOGGER.info("MDCErrorDelegate");
        throw new RuntimeException("MDCErrorDelegate");
    }
}
