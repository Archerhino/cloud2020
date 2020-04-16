package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.enumeration.ResponseStatus;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if(result > 0){
            log.info("The result is: {}", result);
            return new CommonResult<>(ResponseStatus.SUCCESS, "Insertion success! Server Port: " + serverPort, result);
        }else{
            return new CommonResult<>(ResponseStatus.FAIL, "Insertion fail...");
        }
    }

    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment != null){
            log.info("The serial is: {}", payment.getSerial());
            return new CommonResult<>(ResponseStatus.SUCCESS, "Search success! Server Port: " + serverPort, payment);
        }else{
            return new CommonResult<>(ResponseStatus.FAIL, "No related record with id: " + id);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services){
            log.info("**********Service: {}", service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for(ServiceInstance instance : instances){
            log.info("**********{}\t{}\t{}\t{}\t{}", instance.getServiceId(), instance.getInstanceId(), instance.getHost(), instance.getPort(), instance.getUri());
        }

        return this.discoveryClient;
    }
}
