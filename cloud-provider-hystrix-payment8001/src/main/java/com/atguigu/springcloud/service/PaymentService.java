package com.atguigu.springcloud.service;

public interface PaymentService {
    String paymentInfo(Integer id);
    String paymentInfoMockingTimeout(Integer id);
    String paymentCircuitBreaker(Integer id);
}
