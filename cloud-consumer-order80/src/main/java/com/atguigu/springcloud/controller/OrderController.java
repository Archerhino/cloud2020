package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.enumeration.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/entity")
    public CommonResult<Payment> createEntity(Payment payment){
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment", payment, CommonResult.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            log.info("Status Code: {}, Header: {}", responseEntity.getStatusCodeValue(), responseEntity.getHeaders());
            return responseEntity.getBody();
        }else{
            return new CommonResult<>(ResponseStatus.FAIL, "fail");
        }
    }

    @GetMapping(value = "/consumer/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/entity/{id}")
    public CommonResult<Payment> getPaymentEntityById(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/" + id, CommonResult.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            log.info("Status Code: {}, Header: {}", responseEntity.getStatusCodeValue(), responseEntity.getHeaders());
            return responseEntity.getBody();
        }else{
            return new CommonResult<>(ResponseStatus.FAIL,"fail");
        }
    }
}
