package com.iweb.controller.fore;

import com.iweb.entity.Order;
import com.iweb.entity.OrderItem;
import com.iweb.entity.Product;
import com.iweb.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
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
            Order order = new Order();
            order.setId(-1);
            orderItem.setOrder(order);
            orderItemService.update(orderItem);
        }else {
            orderItem = new OrderItem();
            orderItem.setNumber(productNumber);
            orderItem.setUser(user);
            Order order = new Order();
            order.setId(-1);
            orderItem.setOrder(order);
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
        req.setAttribute("productNum",productNumber);
        req.setAttribute("product",product);
        return "/page/fore/needLogin/orderItem/directBuy.jsp";
    }

    public String carBuy(HttpServletRequest req,HttpServletResponse resp){
        String[] ids = req.getParameterValues("List");
        User user = (User) req.getSession().getAttribute("foreUser");
        Order order = new Order();
        orderService.add(order);
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            int id = Integer.parseInt(ids[i]);
            OrderItem orderItem = orderItemService.get(id);
            orderItem.setOrder(order);
            orderItemService.update(orderItem);
            int oiNum = orderItemService.getShoppingCartsNum(user.getId());
            req.getSession().setAttribute("oiNum",oiNum);
            orderItems.add(orderItem);
        }
        return "%"+order.getId();
    }

    public String goOrder(HttpServletRequest req,HttpServletResponse resp){
        int oid = Integer.parseInt(req.getParameter("id"));
        List<OrderItem> orderItems = orderItemService.listByOid(oid);
        req.setAttribute("orderItems",orderItems);
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

    public String updateAddProductNum(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        OrderItem orderItem = orderItemService.get(id);
        Order order = new Order();
        order.setId(-1);
        orderItem.setOrder(order);
        orderItem.setNumber(orderItem.getNumber()+1);
        orderItemService.update(orderItem);
        return "%success";
    }

    public String updateSubProductNum(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        OrderItem orderItem = orderItemService.get(id);
        Order order = new Order();
        order.setId(-1);
        orderItem.setOrder(order);
        orderItem.setNumber(orderItem.getNumber()-1);
        orderItemService.update(orderItem);
        return "%success";
    }

}
