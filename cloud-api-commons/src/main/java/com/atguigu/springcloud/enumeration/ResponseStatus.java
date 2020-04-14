package com.atguigu.springcloud.enumeration;


public enum ResponseStatus {
    SUCCESS(200, "success"),
    FAIL(444, "fail");

    private final int code;
    private final String message;

    ResponseStatus(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
