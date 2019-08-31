package com.linqxxy.servlet;

import com.linqxxy.entiity.Article;
import com.linqxxy.exeception.ParameterExecption;
import com.linqxxy.tools.DBUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleListServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Article> articles=new ArrayList<>();
        String sid=request.getParameter("id");
        Integer id=Integer.parseInt(sid);
        try {
        }catch (NumberFormatException ex){
            throw new ParameterExecption("id错误("+sid+")");
        }
        try {
            conn= DBUtil.getConnection();
            String sql="select a.id,a.title,a.content,a.create_time from article a,user u where a.user_id=u.id and u.id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                Article article=new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setCreateTime(rs.getTimestamp("create_time"));
                articles.add(article);
            }
            return articles;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            DBUtil.close(conn,ps,rs);
        }
    }
}
