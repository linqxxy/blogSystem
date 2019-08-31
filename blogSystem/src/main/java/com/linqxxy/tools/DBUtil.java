package com.linqxxy.tools;

import com.linqxxy.exeception.SystemExecption;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DBUtil {
    private static final String URL = "jdbc:mysql://LOCALHOST:3306/blogdemo?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
    private volatile static DataSource DATASOURCE;
    private static DataSource getDATASOURCE() {
        if (DATASOURCE == null) {
            synchronized (DBUtil.class) {
                if (DATASOURCE == null) {
                    DATASOURCE = new MysqlDataSource();
                    ((MysqlDataSource) DATASOURCE).setUrl(URL);
                    ((MysqlDataSource) DATASOURCE).setUser(USER_NAME);
                    ((MysqlDataSource) DATASOURCE).setPassword(PASSWORD);
                }
            }
        }
        return DATASOURCE;
    }
    public static Connection getConnection(){
        try {
            return getDATASOURCE().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void close(Connection connection, PreparedStatement preparedStatement,
                             ResultSet resultSet){
        try {
            if (resultSet!=null){
                resultSet.close();
            }
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (connection!=null){
                connection.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            throw  new SystemExecption("数据库错误");
        }
    }
}
