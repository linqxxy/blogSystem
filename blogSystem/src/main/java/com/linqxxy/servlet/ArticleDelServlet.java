package com.linqxxy.servlet;

import com.linqxxy.entiity.Article;
import com.linqxxy.exeception.BussinessExecption;
import com.linqxxy.exeception.ParameterExecption;
import com.linqxxy.tools.DBUtil;
import com.linqxxy.tools.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/articleDelete")
public class ArticleDelServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取表单参数
        String[] idsArray=request.getParameter("ids").split(",");
        int[] ids=new int[idsArray.length];
        try {
            for (int i=0;i<idsArray.length;i++){
                ids[i]=Integer.parseInt(idsArray[i]);
                System.out.println(idsArray[i]);
            }
        }catch (Exception ex){
            throw new ParameterExecption("请求参数错误");
        }
        //处理业务逻辑
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            StringBuilder sql = new StringBuilder("delete from article where id in (");
            for (int i=0;i<ids.length;i++){
                if (i==0){
                    sql.append("?");
                }else {
                    sql.append(",?");
                }
            }
            sql.append(")");
            ps=conn.prepareStatement(sql.toString());
            for (int i=0;i<ids.length;i++){
                ps.setInt(i+1,ids[i]);
            }
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return null;
    }
}
