package com.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;


public class MyServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("初始化servlet");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("getServletConfig");
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        System.out.println("service");
        System.out.println(servletRequest.getRemoteAddr());
        System.out.println(servletRequest.getParameter("name"));
        System.out.println(servletRequest.getServerName());
        System.out.println(JSON.toJSONString(servletRequest.getServletContext()));
        // 设置响应内容类型
        servletResponse.setContentType("text/html");

        // 实际的逻辑是在这里
        PrintWriter out = servletResponse.getWriter();
        out.println("<h1>" + servletRequest.getProtocol() + "</h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
