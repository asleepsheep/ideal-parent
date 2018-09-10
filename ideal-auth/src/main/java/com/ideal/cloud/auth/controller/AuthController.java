package com.ideal.cloud.auth.controller;


import com.ideal.cloud.auth.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiangfan
 * 2018/8/8 19:30
 */
@RestController
public class AuthController {

    @Autowired
    private AuthClient authClient;

    /**
     * auth自身方法
     *
     * @return String
     */
    @GetMapping("/authMsg")
    public String authMsg() {
        return "I am authMsg 2";
    }

    /**
     * 调用monitor服务的monitorMsg方法，并返回结果
     *
     * @return authMessage
     */
    @GetMapping("/monitorMsg")
    
    public String getMonitorMessage() {
        String monitorMessage = authClient.getMonitorMsg();

        return "看看monitor那边说了什么消息：" + monitorMessage;
    }

    
    @Value("${hello}")
    private String hello;

    @RequestMapping("/hello")
    public String from() {
        return this.hello;
    }
}
