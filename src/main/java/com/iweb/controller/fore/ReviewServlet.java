package com.iweb.controller.fore;

import com.iweb.entity.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
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
        Review review = new Review();
        Product product = productService.get(pid);
        review.setProduct(product);
        review.setContent(comment);
        review.setUser(user);
        if ("true".equals(anonymity)){
            String fakeName = user.getAnonymousName();
            review.setNickname(fakeName);
        }else {
            review.setNickname(user.getName());
        }
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

    public String list(HttpServletRequest req,HttpServletResponse resp){
        User user = (User) req.getSession().getAttribute("foreUser");
        List<Review> reviews = reviewService.listUserReviews(user.getId());
        req.setAttribute("reviews",reviews);
        return "/page/fore/needLogin/review/listReview.jsp";
    }

    public String delete(HttpServletRequest req,HttpServletResponse resp){
        int rid = Integer.parseInt(req.getParameter("id"));
        reviewService.delete(rid);
        return "%success";
    }

    public String edit(HttpServletRequest req,HttpServletResponse resp){
        int rid = Integer.parseInt(req.getParameter("id"));
        Review review = reviewService.get(rid);
        req.setAttribute("review",review);
        return "/page/fore/needLogin/review/editReview.jsp";
    }

    public String update(HttpServletRequest req,HttpServletResponse resp){
        int rid = Integer.parseInt(req.getParameter("rid"));
        String content = req.getParameter("content");
        Review review = reviewService.get(rid);
        review.setContent(content);
        reviewService.update(review);
        return "@/fore_review_list";
    }
}
