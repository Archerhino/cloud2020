package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageSenderController {
    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/messageSender")
    public String sendMessage(){
        return messageProvider.send();
    }
}
