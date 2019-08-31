package com.linqxxy.exeception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BussinessExecption extends RuntimeException{
    private String code;
    public BussinessExecption(String message) {
        super("业务执行异常"+message);
        code="401";
    }

    public BussinessExecption(String message, Throwable cause) {
        super("业务执行异常"+message, cause);
        code="401";
    }
}
