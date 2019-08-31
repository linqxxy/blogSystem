package com.linqxxy.servlet;

import com.linqxxy.tools.MyActionEnter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/ueditor")
public class UEditorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path= Objects.requireNonNull(UEditorServlet.class.getClassLoader().
                getResource("config.json")).getPath();
        MyActionEnter actionEnter=new MyActionEnter(req,path);
        String exec=actionEnter.exec();
        resp.getWriter().write(exec);
    }
}
