package com.servlet;

import com.utils.JudgeEncode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//-Dfile.encoding=utf-8
public class MyHttpServlet extends HttpServlet {
    // 处理 GET 方法请求的方法
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置刷新自动加载时间为 5 秒
       // response.setIntHeader("Refresh", 10);
        response.reset();
       // response.sendError(407, "Need authentication!!!" );
        // 设置响应内容类型
       // request.setCharacterEncoding("GBK");
        response.setContentType("text/html;charset=UTF-8");
        String name="新建文本文档.txt";
        try {
            System.out.println("testwwwww");
            byte[] nameByte=name.getBytes();
            System.out.println(name);
            System.out.println("+++++++++++++++++++++++");
            for (byte b : nameByte) {
                System.out.println(b);
            }
            System.out.println("+++++++++++++++++++++++");
            System.out.println(JudgeEncode.getEncoding(name));
//            System.out.println(name);
//            name=new String(name.getBytes(),"GB2312");
//            System.out.println(name);
//            System.out.println(JudgeEncode.getEncoding(name));
//            name=new String(name.getBytes(),"GBK");
//            System.out.println(name);
//            System.out.println(JudgeEncode.getEncoding(name));
            name=new String(name.getBytes("GB2312"),"UTF-8");
            System.out.println(name);
            System.out.println(JudgeEncode.getEncoding(name));
            name=new String(name.getBytes(),"ISO-8859-1");
            System.out.println(name);
            System.out.println(JudgeEncode.getEncoding(name));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //使用默认时区和语言环境获得一个日历
        Calendar cale = Calendar.getInstance();
        //将Calendar类型转换成Date类型
        Date tasktime=cale.getTime();
        //设置日期输出的格式
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化输出
        String nowTime = df.format(tasktime);
        PrintWriter out = response.getWriter();
        String title = "自动刷新 Header 设置 - 菜鸟教程实例";
        String docType =
                "<!DOCTYPE html>\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n"+
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" +name+ "</h1>\n" +
                "<p>当前时间是：" + nowTime + "</p>\n");
    }
    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
