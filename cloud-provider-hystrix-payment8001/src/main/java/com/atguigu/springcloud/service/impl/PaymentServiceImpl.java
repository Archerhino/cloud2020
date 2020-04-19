package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo(Integer id) {
        String result = Thread.currentThread().getName() + " paymentInfo_Ok, id: " + id;
        return result;
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfoMockingTimeout(Integer id) {
        int sleepTimeSecond = 2000;
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTimeSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int age = 10 / 0;
        return Thread.currentThread().getName() + " paymentInfo_Timeout, id: " + id;
    }

    public String paymentInfoTimeoutHandler(Integer id){
        return Thread.currentThread().getName() + " System is busy, please retry...id: " + id;
    }
}
