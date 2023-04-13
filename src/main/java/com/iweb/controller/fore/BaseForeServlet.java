package com.iweb.controller.fore;

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
 * @date 2023/4/7 15:35
 */
public class BaseForeServlet extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();
    ProductService productService = new ProductServiceImpl();
    UserService userService = new UserServletImpl();
    OrderItemService orderItemService = new OrderItemServiceImpl();
    PropertyValueService propertyValueService = new PropertyValueServiceImpl();
    ReviewService reviewService = new ReviewServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String method = (String) req.getAttribute("method");
            Method m = this.getClass().getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            String redirect = m.invoke(this,req,resp).toString();
            if (redirect.startsWith("@")){
                resp.sendRedirect(redirect.substring(1));
            }else if (redirect.startsWith("%")){
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().print(redirect.substring(1));
            }else {
                req.getRequestDispatcher(redirect).forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
