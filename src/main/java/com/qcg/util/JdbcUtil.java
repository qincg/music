package com.qcg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接工具
 */
public class JdbcUtil {

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        XmlUtil xmlUtil = new XmlUtil();
        String userName = xmlUtil.getValue("userName");
        String password = xmlUtil.getValue("password");
        String driverUrl = xmlUtil.getValue("url");
        String driverName = xmlUtil.getValue("driverName");
        Connection connection = null;
        try {
            //加载驱动
            Class.forName(driverName);
            connection = DriverManager.getConnection(driverUrl,userName,password);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            LogUtil.getLogger().error(e.getMessage());
        }catch (SQLException e){
            e.printStackTrace();
            LogUtil.getLogger().error(e.getMessage());
        }
        return connection;
    }

    /**
     * 关闭连接
     * @param connection
     */
    public static void closeConnection(Connection connection){
        if (connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
