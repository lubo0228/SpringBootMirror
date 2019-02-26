package com.boot.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by L on 2019/2/26.
 */
public class JobEventListener implements ActivitiEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobEventListener.class);

    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
        ActivitiEventType eventType = activitiEvent.getType();
        String name = eventType.name();
        if (name.startsWith("JOB") || name.startsWith("TIMER")) {
            LOGGER.info("监听到job事件 = {}", eventType);
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
