package com.ideal.monitor.controller;

import com.ideal.monitor.client.MonitorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiangfan
 * 2018/8/8 19:30
 */
@RestController
public class MonitorController {

    @Autowired
    private MonitorClient monitorClient;

    /**
     * monitor自身方法
     *
     * @return String
     */
    @GetMapping("/monitorMsg")
    public String msg() {

        return "I am monitor";
    }

    /**
     * 调用auth服务的authMsg方法，并返回结果
     *
     * @return authMessage
     */
    @GetMapping("/authMsg")
    public String getAuthMessage() {
        String authMessage = monitorClient.getMonitorMsg();

        return "看看auth那边说了什么消息：" + authMessage;
    }
}
