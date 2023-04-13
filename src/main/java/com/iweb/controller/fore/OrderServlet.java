package com.iweb.controller.fore;

import com.iweb.entity.Order;
import com.iweb.entity.OrderItem;
import com.iweb.entity.Product;
import com.iweb.entity.User;
import com.iweb.util.OrderCodeUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yang
 * @date 2023/4/12 22:03
 */
@WebServlet("/orderServlet")
public class OrderServlet extends BaseForeServlet {
    public String update(HttpServletRequest req, HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("orderId"));
        Order order = orderService.get(id);
        order.setPayDate(new Date());
        order.setStatus("waitDelivery");
        orderService.update(order);
        return "@/fore_order_listOrder";
    }

    public String add(HttpServletRequest req,HttpServletResponse resp){
        String address = req.getParameter("address");
        String post = req.getParameter("post");
        String receiver = req.getParameter("receiver");
        String mobile = req.getParameter("mobile");
        String userMessage = req.getParameter("userMessage");
        User user = (User) req.getSession().getAttribute("foreUser");
        String orderCode = OrderCodeUtil.getOrderId(user.getId());
        Order order = new Order();
        order.setOrderCode(orderCode);
        order.setAddress(address);
        order.setPost(post);
        order.setReceiver(receiver);
        order.setMobile(mobile);
        order.setUserMessage(userMessage);
        order.setUser(user);
        order.setStatus("waitPay");
        orderService.add(order);
        order = orderService.get(orderCode);
        int pid = Integer.parseInt(req.getParameter("pid"));
        int number = Integer.parseInt(req.getParameter("pNum"));
        Product product = productService.get(pid);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setNumber(number);
        orderItem.setUser(user);
        orderItem.setProduct(product);
        orderItemService.add(orderItem);
        product.setStock(product.getStock()-number);
        productService.update(product);
        order = orderService.get(orderCode);
        req.setAttribute("order",order);
        return "/page/fore/needLogin/order/payView.jsp";
    }

    public String listOrder(HttpServletRequest req,HttpServletResponse resp){
        User user = (User)req.getSession().getAttribute("foreUser");
        List<Order> orders = orderService.list(user.getId());
        req.setAttribute("orders",orders);
        return "/page/fore/needLogin/order/myOrder.jsp";
    }

    public String delete(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        orderService.delete(id);
        return "%success";
    }

    public String payView(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("orderId"));
        Order order = orderService.get(id);
        req.setAttribute("order",order);
        return "/page/fore/needLogin/order/payView.jsp";
    }

    public String comment(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("orderId"));
        Order order = orderService.get(id);
        Set<Product> products = new HashSet<>();
        List<OrderItem> ois = order.getOrderItems();
        for (OrderItem oi:ois) {
            products.add(oi.getProduct());
        }
        req.setAttribute("orderId",id);
        if (null==req.getSession().getAttribute("productsComment")){
            req.getSession().setAttribute("productsComment",products);
        }
        return "/page/fore/needLogin/review/commentProduct.jsp";
    }
}
