package com.learn.jeffrey.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/6/12 10:58
 **/
public class JDBCUtils {
    //jdbc协议:子协议://ip:端口号/数据库名
    public static String url = "jdbc:mysql://localhost:3306/springdemo";

    private static Connection connection = null;
    /**
     * 获取JDBC连接
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            // 注册或加载驱动程序，通过反射创建一个driver对象
            Class.forName("com.mysql.jdbc.Driver");
            Class c = Driver.class;
            // 获取数据连接对象，在返回connection对象之前，DriverManager它内部会先校验驱动对象driver信息对不对。
            connection = DriverManager.getConnection(url,"root","admin");
            return connection;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement();
        connection.prepareStatement("");
        ResultSet resultSet = statement.executeQuery("select * from user ");
        while (resultSet.next()){
            String id=resultSet.getString("id");
            String nickname=resultSet.getString("nickname");
            String password=resultSet.getString("password");
            System.out.println("id:"+id+"nickname:"+nickname+"password:"+password);
        }

    }
}
