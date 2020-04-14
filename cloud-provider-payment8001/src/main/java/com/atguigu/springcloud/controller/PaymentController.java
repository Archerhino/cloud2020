package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.enumeration.ResponseStatus;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if(result > 0){
            log.info("The result is: {}", result);
            return new CommonResult<>(ResponseStatus.SUCCESS, "Insertion success!", result);
        }else{
            return new CommonResult<>(ResponseStatus.FAIL, "Insertion fail...");
        }
    }

    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment != null){
            log.info("The serial is: {}", payment.getSerial());
            return new CommonResult<>(ResponseStatus.SUCCESS, "Search success!", payment);
        }else{
            return new CommonResult<>(ResponseStatus.FAIL, "No related record with id: " + id);
        }
    }
}
