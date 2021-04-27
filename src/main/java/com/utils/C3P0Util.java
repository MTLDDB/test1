package com.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : huang
 * @date :  2021/2/1
 * @time : 9:56
 * To change this template use File | Settings | File and Code Templates.
 */
public class C3P0Util {
    //使用ComboPooledDataSource来生成DataSource的实例
    private static DataSource dataSource = new ComboPooledDataSource();
    private static DataSource dataSourceOther;


    //从连接池中获取连接
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
    //从连接池中获取连接
    public static DataSource init(String name){
        return dataSourceOther=new ComboPooledDataSource(name);
    }
    public static Connection getConnectionOther(String name) {
        try {

            return dataSource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    //释放连接回连接池
    public static void release(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {

            try {
               // System.out.println("destory");
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }


}
