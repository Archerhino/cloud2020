package com.atguigu.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private HttpStatus code;
    private String message;
    private T data;

    public CommonResult(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
}
