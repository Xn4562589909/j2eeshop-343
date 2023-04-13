package com.iweb.controller.admin;

import com.iweb.service.*;
import com.iweb.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Yang
 * @date 2023/3/30 15:22
 */
public abstract class BaseBackServlet extends HttpServlet {
    UserService userService = new UserServletImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    PropertyService propertyService = new PropertyServiceImpl();
    ProductService productService = new ProductServiceImpl();
    PropertyValueService propertyValueService = new PropertyValueServiceImpl();
    ProductImageService productImageService = new ProductImageServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 当我们通过前面的过滤器 访问到CategoryServlet的时候
        // 由于CategoryServlet并没有重写service方法
        // 默认会调用父类的service方法
        try {
            // 从请求中获取method属性值
            String method = (String)req.getAttribute("method");
            // 利用反射 调用method方法
            Method m = this.getClass().getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            // 利用反射执行方法 根据方法的返回值 获取后续要进行跳转的路径
            String redirect = m.invoke(this,req,resp).toString();
            // 根据路径决定后续如何操作
            // 我们定义 以不同的路径开头表示不同的访问方式
            if (redirect.startsWith("@")){
                // 发现跳转路径是@开头的则进行重定向
                resp.sendRedirect(redirect.substring(1));
            }else if (redirect.startsWith("%")){
                // 确定servlet接收的是一个ajax请求 则返回响应的JSON字符串
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().print(redirect.substring(1));
            }else {
                // 如果发现没有开头的特殊符号 则默认做转发
                req.getRequestDispatcher(redirect).forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
