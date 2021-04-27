package com.Dao;

import textACK.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBhelper {
    //tsett
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&amp;allowMultiQueries=true";
    static final String DB_URL = "jdbc:mysql://115.159.40.140:3306/test?characterEncoding=UTF-8&amp;allowMultiQueries=true";
    static final String USER = "root";
    static final String PASS = "1234";
    static final String PASSset = "1234";

    public static Connection getConnection(){
        System.out.println(PASSset);
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void add(String message){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        try {

            String sql;
            sql = "insert into  tb_student (NAME) VALUES (?) ";
            pstmt =  conn.prepareStatement(sql);
            pstmt.setString(1,message);
            int rs = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Person> getPerson() {
        List<Person> list =new ArrayList<Person>();
        Connection conn = getConnection();
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM tb_student";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                Person p=new Person();
                String id  = rs.getString("id");
                String name = rs.getString("name");
                String description = rs.getString("sex");
                p.setId(id);
                p.setName(name);
                p.setDescription(description);
                list.add(p);
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return list;
    }
}
