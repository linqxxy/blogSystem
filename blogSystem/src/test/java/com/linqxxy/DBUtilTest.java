package com.linqxxy;

import com.linqxxy.tools.DBUtil;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class DBUtilTest {

    @Test
    public void testConnection(){
        Connection connection= DBUtil.getConnection();
        //测试数据库连接是否成功
        Assert.assertNotNull(connection);
    }
}
