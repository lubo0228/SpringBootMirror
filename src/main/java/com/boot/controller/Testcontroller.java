package com.boot.controller;

import com.boot.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class Testcontroller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Testcontroller.class);

    /**
     * ?和body都能接收到
     * @param loginName
     */
    @PostMapping("param")
    public void testRequestParam(@RequestParam String loginName) {
        LOGGER.info("loginName：" + loginName);
    }

    /**
     * 处理application/json
     * @param user
     */
    @PostMapping("body")
    public void testRequestBody(@RequestBody User user) {
        LOGGER.info("loginName：" + user.getLoginName());
    }


    /**
     * 处理application/x-www-form-urlcoded
     * @param map
     */
    @PostMapping("body2")
    public void testRequestBody(@RequestBody MultiValueMap<String,String> map) {
        LOGGER.info("loginName：" + map.get("loginName"));
    }

}
