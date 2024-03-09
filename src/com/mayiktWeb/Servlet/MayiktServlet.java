package com.mayiktWeb.Servlet;

import javax.servlet.*;
import java.io.IOException;

public class MayiktServlet implements Servlet {

    /**
     * 1.创建Servlet对象    我们怎么知道我这个对象创建没有, 类中构造方法 类被new   执行构造方法
     * 2.init初始化方法
     * 3.service方法
     * 4.destroy Tomcat停止时
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
