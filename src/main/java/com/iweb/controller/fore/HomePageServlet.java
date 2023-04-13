package com.iweb.controller.fore;

import com.iweb.entity.Category;
import com.iweb.entity.Product;
import com.iweb.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/10 0:58
 */
@WebServlet("/homePageServlet")
public class HomePageServlet extends BaseForeServlet{
    public String list(HttpServletRequest req, HttpServletResponse resp){
        List<Category> categories = categoryService.list();
        req.setAttribute("categories",categories);
//        List<Product> products = productService.list();
//        req.setAttribute("products",products);
        return "/page/fore/noLogin/homePage.jsp";
    }
}
