package com.atguigu.springcloud.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.atguigu.springcloud.enumeration.ResponseStatus;

@Data
@NoArgsConstructor
public class CommonResult<T> {
    private int code;
    private String message;
    private T data;

    public CommonResult(ResponseStatus status, String message, T data) {
        this.code = status.getCode();
        this.message = message;
        this.data = data;
    }
    public CommonResult(ResponseStatus status, String message) {
        this.code = status.getCode();
        this.message = message;
    }
}
