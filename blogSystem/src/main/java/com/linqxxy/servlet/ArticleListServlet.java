package com.linqxxy.servlet;

import com.entiity.Article;
import com.entiity.User;
import com.linqxxy.tools.DBUtil;
import com.linqxxy.tools.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleListServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Connection conn= DBUtil.getConnection();
        String sql="select a.id,a.title,a.content,a.create_time from article a,user u where a.user_id=u.id and u.id=?";
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setInt(1,Integer.valueOf(request.getParameter("id")));
        ResultSet rs=statement.executeQuery();
        List<Article> articles=new ArrayList<>();
        while (rs.next()){
            Article article=new Article();
            article.setId(rs.getInt("id"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setCreate_time(rs.getTimestamp("create_time"));
            articles.add(article);
        }
        return articles;
    }
}
