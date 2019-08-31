package com.linqxxy.servlet;

import com.linqxxy.entiity.Article;
import com.linqxxy.exeception.BussinessExecption;
import com.linqxxy.exeception.ParameterExecption;
import com.linqxxy.exeception.SystemExecption;
import com.linqxxy.tools.DBUtil;
import com.linqxxy.tools.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArticleAddServlet extends BaseServlet {
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
            String sql = "insert into article (title,content,user_id,create_time) select\n" +
                    "    ?,?,user.id,now() from user where user.name=?;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,article.getTitle());
            ps.setString(2,article.getContent());
            ps.setString(3,article.getUserAccout());
            int r=ps.executeUpdate();
            if (r>0){
                return r;
            }else {
                throw new BussinessExecption("用户不存在"+article.getUserAccout());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }
}
