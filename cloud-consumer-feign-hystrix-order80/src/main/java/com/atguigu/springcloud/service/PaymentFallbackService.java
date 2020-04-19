package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo(Integer id) {
        return "----PaymentFallbackService fallback-paymentInfoOK";
    }

    @Override
    public String paymentInfoMockingTimeout(Integer id) {
        return "----PaymentFallbackService fallback-paymentInfoTimeOut";
    }
}
