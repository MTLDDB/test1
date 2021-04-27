package com.servlet;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class JsonHttpServlet  extends HttpServlet {


    private static  int value=1;

    // 处理 GET 方法请求的方法
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        JSONObject object = new JSONObject();
        object.put("kind", "attr");
        object.put("message", "login Success");
        object.put("code", 200);
        object.put("token", UUID.randomUUID().toString());
        object.put("attrValue", value++);
        if(value==101){
            value=1;
        }
        System.out.println(object);
        response.getWriter().print(object);
    }
    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }


}
