package com.linqxxy.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linqxxy.exeception.SystemExecption;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

public class JSONUtil {
    public static String objFormat(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            return objectMapper.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new SystemExecption("Json解析错误" + obj);
        }
    }

    public static <T>T strFormat(HttpServletRequest request, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try{
         return    objectMapper.readValue(request.getInputStream(),clazz);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new SystemExecption("Json反序列化失败");
        }
    }
}
