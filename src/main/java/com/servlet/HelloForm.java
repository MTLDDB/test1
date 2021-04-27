package com.servlet;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.Cookie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.UUID;

public class HelloForm extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//        // 为名字和姓氏创建 Cookie
//        Cookie name = new Cookie("name",
//                URLEncoder.encode(request.getParameter("name"), "UTF-8")); // 中文转码
//        Cookie url = new Cookie("url",
//                request.getParameter("url"));
//
//        // 为两个 Cookie 设置过期日期为 24 小时后
//        name.setMaxAge(60*60*24);
//        url.setMaxAge(60*60*24);
//
//        // 在响应头中添加两个 Cookie
//        response.addCookie( name );
//        response.addCookie( url );
//
//        // 设置响应内容类型
//        response.setContentType("text/html;charset=UTF-8");
//
//        PrintWriter out = response.getWriter();
//        String title = "设置 Cookie 实例";
//        String docType = "<!DOCTYPE html>\n";
//        out.println(docType +
//                "<html>\n" +
//                "<head><title>" + title + "</title></head>\n" +
//                "<body bgcolor=\"#f0f0f0\">\n" +
//                "<h1 align=\"center\">" + title + "</h1>\n" +
//                "<ul>\n" +
//                "  <li><b>站点名：</b>："
//                + request.getParameter("name") + "\n</li>" +
//                "  <li><b>站点 URL：</b>："
//                + request.getParameter("url") + "\n</li>" +
//                "</ul>\n" +
//                "</body></html>");

        JSONObject object = new JSONObject();
        object.put("name", "huang");
        object.put("code", 200);
        object.put("message", "getInfo Success");
        object.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80");
        System.out.println(object);
        response.getWriter().print(object);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
