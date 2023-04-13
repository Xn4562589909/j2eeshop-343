package com.iweb.controller.fore;

import com.iweb.entity.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yang
 * @date 2023/4/13 5:01
 */
@WebServlet("/reviewServlet")
public class ReviewServlet extends BaseForeServlet {
    public String comment(HttpServletRequest req, HttpServletResponse resp){
        int pid = Integer.parseInt(req.getParameter("productId"));
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        String comment = req.getParameter("comment");
        String anonymity = req.getParameter("anonymity");
        User user = (User) req.getSession().getAttribute("foreUser");
        Review review = new Review();
        Product product = productService.get(pid);
        review.setProduct(product);
        review.setContent(comment);
        if ("true".equals(anonymity)){
            String fakeName = user.getAnonymousName();
            user.setName(fakeName);
        }
        review.setUser(user);
        reviewService.add(review);
        int num = (int) req.getSession().getAttribute("pSize");
        if (num==1){
            Order order = orderService.get(orderId);
            order.setStatus("finish");
            orderService.update(order);
            req.getSession().removeAttribute("pSize");
            return "@/fore_order_listOrder";
        }else {
            num -= 1;
            req.getSession().setAttribute("pSize",num);
            return "@/fore_order_comment?orderId="+orderId;
        }
    }
}
