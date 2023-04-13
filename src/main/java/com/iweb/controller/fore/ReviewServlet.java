package com.iweb.controller.fore;

import com.iweb.entity.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Set;

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
        User commentUser = new User();
        commentUser.setId(user.getId());
        commentUser.setName(user.getName());
        Review review = new Review();
        Product product = productService.get(pid);
        review.setProduct(product);
        review.setContent(comment);
        if ("true".equals(anonymity)){
            String fakeName = user.getAnonymousName();
            commentUser.setName(fakeName);
        }
        review.setUser(commentUser);
        reviewService.add(review);
        Set<Product> productsComment = (Set<Product>) req.getSession().getAttribute("productsComment");
        if (productsComment.size()==1){
            Order order = orderService.get(orderId);
            order.setStatus("finish");
            orderService.update(order);
            req.getSession().removeAttribute("productsComment");
            return "@/fore_order_listOrder";
        }else {
            Iterator<Product> it = productsComment.iterator();
            while (it.hasNext()){
                if (it.next().getId()==pid){
                    it.remove();
                }
            }
            req.getSession().setAttribute("productsComment",productsComment);
            return "@/fore_order_comment?orderId="+orderId;
        }
    }
}
