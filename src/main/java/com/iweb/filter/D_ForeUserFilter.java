package com.iweb.filter;

import com.iweb.entity.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yang
 * @date 2023/4/10 5:18
 */
@WebFilter("/*")
public class D_ForeUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        // 获取uri
        String uri = req.getRequestURI();
        // 判断请求访问是否是登录界面或者是提交登录信息的请求
        // 如果是这两个请求 必须直接放行 否则会进入死循环
        if(uri.startsWith("/admin")||uri.endsWith("login")||uri.endsWith("gif")||uri.endsWith("login.jsp")||uri.endsWith("register.jsp")
                ||uri.endsWith("show")|| uri.endsWith("register")||uri.endsWith(".png")||uri.endsWith("jpg")||uri.startsWith("/page/fore/noLogin")
                ||uri.endsWith("List")||uri.endsWith("list") || uri.endsWith("css")||uri.endsWith("js")||uri.startsWith("/page/admin")){
            filterChain.doFilter(req,resp);
            return;
        }
        // 我们这里省略cookie的操作 我们假设 登录成功 就直接把用户名存放在当前session中
        // 这里过滤器就应该从session中获取用户名 判断是否为空 来判断用户是否成功登录
        User user = (User) req.getSession().getAttribute("foreUser");
        if (null==user){
            resp.sendRedirect("/page/fore/noLogin/login.jsp");
            return;
        }
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
