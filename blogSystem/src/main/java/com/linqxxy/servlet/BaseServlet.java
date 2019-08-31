package com.linqxxy.servlet;

import com.linqxxy.entiity.JSON;
import com.linqxxy.exeception.ParameterExecption;
import com.linqxxy.exeception.SystemExecption;
import com.linqxxy.tools.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        JSON result = new JSON();
        try {
            Object data=process(req,resp);
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("操作成功");
            result.setData(data);
        }catch (Exception e){
            if (e instanceof ParameterExecption){
                result.setCode(((ParameterExecption) e).getCode());
                result.setMessage(e.getMessage());
            }else if (e instanceof SystemExecption){
                result.setCode(((SystemExecption) e).getCode());
                result.setMessage(e.getMessage());
            }else {
                result.setCode("500");
                result.setMessage("服务器错误");
            }
        }
        //TODO 待处理异常(无法解决)
        resp.getWriter().write(Objects.requireNonNull(JSONUtil.objFormat(result)));
    }
    public abstract Object process(HttpServletRequest request,HttpServletResponse response)throws Exception;

}
