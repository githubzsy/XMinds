package com.zhoushiya.interview.spiDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author zhoushiya
 * @date 2020/9/9 13:14
 */
public class SpiDemo {
    public static void main(String[] args) {
        System.out.println(DriverManager.class.getClassLoader());
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/shop?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT", "root", "welcome")) {
            //com.mysql.cj.jdbc.ConnectionImpl@6fb0d3ed 接口类型为java.sql.Connection 实际类型为com.mysql.cj.jdbc.ConnectionImpl 还是第三方类型
            System.out.println(connection);
            //sun.misc.Launcher$AppClassLoader@18b4aac2 还是AppClassLoader
            System.out.println(connection.getClass().getClassLoader());
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }
}
