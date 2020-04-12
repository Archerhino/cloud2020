package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("The result is: {}", result);
        if(result > 0){
            return new CommonResult(HttpStatus.OK, "Insertion success!", result);
        }
        return new CommonResult(HttpStatus.BAD_REQUEST, "Insertion fail...");
    }

    @GetMapping(value = "/payment/{id}")
    public CommonResult getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("The serial is: {}", payment.getSerial());
        if(payment != null){
            return new CommonResult(HttpStatus.OK, "Search success!", payment);
        }
        return new CommonResult(HttpStatus.BAD_REQUEST, "No related record with id: " + id);
    }
}
