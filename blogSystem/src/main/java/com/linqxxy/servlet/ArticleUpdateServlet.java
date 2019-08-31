package com.linqxxy.servlet;

import com.linqxxy.entiity.Article;
import com.linqxxy.exeception.BussinessExecption;
import com.linqxxy.tools.DBUtil;
import com.linqxxy.tools.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
//        String userAccount=request.getParameter("userAccount");
//        String title=request.getParameter("title");
//        String content=request.getParameter("content");
        Article article= JSONUtil.strFormat(request,Article.class);
        System.out.println(article);
        try {
            conn = DBUtil.getConnection();
            String sql = "update article set title=?,content=?where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,article.getTitle());
            ps.setString(2,article.getContent());
            ps.setInt(3,article.getId());
            int r=ps.executeUpdate();
            if (r>0){
                return r;
            }else {
                throw new BussinessExecption("文章不存在"+article.getId());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }
}
