package com.iweb.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 根据session判断 用户是否登录
 * 我们应该提前准备好对应的url字符串数组
 * 如果发现用户访问的路径是无需登录就可以访问的 请求放行
 * 如果发现用户访问的路径 是需要登录才可以访问的 做session校验
 * 如果发现用户没有登录 却访问了需要登录才可以查看的页面 则拦截
 * if(uri.startsWith("/fore")
 * @author Yang
 * @date 2023/4/7 15:36
 */
@WebFilter("/*")
public class F_ForeServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        String uri = req.getRequestURI();
        uri = StringUtils.remove(uri,contextPath);
        if (uri.startsWith("/fore")){
            String servletPath = StringUtils.substringBetween(uri,"_","_")+"Servlet";
            String method = StringUtils.substringAfterLast(uri,"_");
            req.setAttribute("method",method);
            req.getRequestDispatcher("/"+servletPath).forward(req,resp);
            return;
        }
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
