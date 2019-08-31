package com.linqxxy.exeception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemExecption extends RuntimeException {
    private String code;
    public SystemExecption(String message) {
        super("系统异常:"+message);
        code="501";
    }

    public SystemExecption(String message, Throwable cause) {
        super("系统异常:"+message, cause);
        code="501";
    }
}
