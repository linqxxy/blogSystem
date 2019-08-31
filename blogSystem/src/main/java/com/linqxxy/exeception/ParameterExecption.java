package com.linqxxy.exeception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParameterExecption extends RuntimeException {
    private String code;
    public ParameterExecption() {
    }

    public ParameterExecption(String message) {
        super("客户端错误"+message);
        code="400";
    }

    public ParameterExecption(String message, Throwable cause) {
        super("客户端错误"+message, cause);
        code="400";
    }
}
