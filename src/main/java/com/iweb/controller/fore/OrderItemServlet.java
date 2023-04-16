package com.iweb.controller.fore;

import com.iweb.entity.OrderItem;
import com.iweb.entity.Product;
import com.iweb.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/12 17:56
 */
@WebServlet("/orderItemServlet")
public class OrderItemServlet extends BaseForeServlet {
    public String joinCar(HttpServletRequest req, HttpServletResponse resp){
        int productNumber = Integer.parseInt(req.getParameter("productNumber"));
        int productId = Integer.parseInt(req.getParameter("productId"));
        User user = (User) req.getSession().getAttribute("foreUser");
        OrderItem orderItem = orderItemService.getCarOrderItemByPid(productId);
        if (null != orderItem){
            int newNumber = productNumber+orderItem.getNumber();
            orderItem.setNumber(newNumber);
            orderItemService.update(orderItem);
        }else {
            orderItem = new OrderItem();
            orderItem.setNumber(productNumber);
            orderItem.setUser(user);
            orderItem.setProduct(productService.get(productId));
            orderItemService.add(orderItem);
            int oiNum = (int)req.getSession().getAttribute("oiNum")+1;
            req.getSession().setAttribute("oiNum",oiNum);
        }
        return "%success";
    }

    public String directBuy(HttpServletRequest req,HttpServletResponse resp){
        int productNumber = Integer.parseInt(req.getParameter("productNumber"));
        int productId = Integer.parseInt(req.getParameter("productId"));
        Product product = productService.get(productId);
        BigDecimal totalPrice = product.getPromotePrice().multiply(new BigDecimal(productNumber));
        req.setAttribute("totalPrice",totalPrice);
        req.setAttribute("productNum",productNumber);
        req.setAttribute("product",product);
        return "/page/fore/needLogin/orderItem/directBuy.jsp";
    }

    public String shoppingCar(HttpServletRequest req,HttpServletResponse resp){
        User user = (User) req.getSession().getAttribute("foreUser");
        List<OrderItem> orderItems = orderItemService.listCar(user.getId());
        req.setAttribute("orderItems",orderItems);
        return "/page/fore/needLogin/orderItem/shoppingCar.jsp";
    }

    public String delete(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        orderItemService.delete(id);
        int oiNum = (int)req.getSession().getAttribute("oiNum")-1;
        req.getSession().setAttribute("oiNum",oiNum);
        return "%success";
    }

}
