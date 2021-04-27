package testSolr.Dao;

import test.model.Task;
import testSolr.model.Product;
import textACK.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBhelper {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static Connection getConnnect(String DB_URL, String USER, String PASS) {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static List<Person> getPerson() {
        final String DB_URL = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&amp;allowMultiQueries=true";
        final String USER = "root";
        final String PASS = "123";
        List<Person> list = new ArrayList<Person>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnnect(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM tb_student";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                Person p = new Person();
                String id = rs.getString("id");
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
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return list;
    }

    public static List<Task> getTask(String objectid,int pageSize) {
        final String DB_URL = "jdbc:mysql://192.168.1.238:9696/kingshard?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
        final String USER = "ds";
        final String PASS = "ameyads";
        List<Task> list = new ArrayList<Task>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnnect(DB_URL, USER, PASS);
            String sql = "select * from tt_mytask where status=0 and objectid> ? order by objectid asc limit ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,objectid);
            stmt.setInt(2, pageSize);
            ResultSet rs = stmt.executeQuery();
            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                Task task = new Task();
                String id = rs.getString("objectid");
                String url = rs.getString("url");
                int status = rs.getInt("status");
                task.setObjectid(id);
                task.setUrl(url);
                task.setStatus(status);
                list.add(task);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return list;
    }

    public static List<Product> getProduct() {
        final String DB_URL = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&amp;allowMultiQueries=true";
        final String USER = "root";
        final String PASS = "123";
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnnect(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM t_product";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                Product p = new Product();
                String id = rs.getString("objectid");
                String name = rs.getString("mpn");
                p.setObjectid(id);
                p.setMpn(name);
                list.add(p);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return list;
    }
}
