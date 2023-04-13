package com.iweb.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yang
 * @date 2023/3/30 15:09
 */
@WebFilter(urlPatterns = "/*")
public class E_BackServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        // localhost/admin_category_list
        // 如果定义了上下文 上下文就需要进行获取
        String contextPath = req.getContextPath();
        // 获取uri
        String uri = req.getRequestURI();
        // 如果定义了上下文 需要从uri中将上下文给去除
        uri = StringUtils.remove(uri,contextPath);
        // 判断uri是否以/admin_开头 如果是 做相关处理
        if (uri.startsWith("/admin_")){
            // 取出后续要访问的servlet类的名称
            String servletPath = StringUtils.substringBetween(uri,"_","_")+"Servlet";
            // 取出后续想要访问的方法名称
            String method = StringUtils.substringAfterLast(uri,"_");
            // 将方法名称存入到请求中
            req.setAttribute("method",method);
            // 通过转发跳转到指定Servlet中
            req.getRequestDispatcher("/"+servletPath).forward(req,resp);
            return;
        }
        // 如果不是我们指定的 则请求放行
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
