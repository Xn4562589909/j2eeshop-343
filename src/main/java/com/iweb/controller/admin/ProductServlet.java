package com.iweb.controller.admin;

import com.iweb.entity.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/3 23:32
 */
@WebServlet("/productServlet")
public class ProductServlet extends BaseBackServlet {
    public String list(HttpServletRequest req, HttpServletResponse resp){
        int cid = Integer.parseInt(req.getParameter("id"));
        List<Product> products = productService.list(cid);
        req.setAttribute("cid",cid);
        req.setAttribute("products",products);
        return "/page/admin/product/listProduct.jsp";
    }

    public String add(HttpServletRequest req,HttpServletResponse resp){
        int cid = Integer.parseInt(req.getParameter("cid"));
        String name = req.getParameter("name");
        String subTitle = req.getParameter("subTitle");
        BigDecimal originalPrice = new BigDecimal(req.getParameter("originalPrice"));
        BigDecimal promotePrice = new BigDecimal(req.getParameter("promotePrice"));
        int stock = Integer.parseInt(req.getParameter("stock"));
        Product product = new Product();
        product.setName(name);
        product.setSubTitle(subTitle);
        product.setCategory(categoryService.get(cid));
        product.setOriginalPrice(originalPrice);
        product.setPromotePrice(promotePrice);
        product.setStock(stock);
        productService.add(product);
        return "@/admin_product_list?id="+cid;
    }

    public String delete(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        productService.delete(id);
        return "%success";
    }

    public String edit(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.get(id);
        req.setAttribute("p",product);
        return "/page/admin/product/editProduct.jsp";
    }

    public String update(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        int cid = Integer.parseInt(req.getParameter("cid"));
        String name = req.getParameter("name");
        String subTitle = req.getParameter("subTitle");
        BigDecimal originalPrice = new BigDecimal(req.getParameter("originalPrice"));
        BigDecimal promotePrice = new BigDecimal(req.getParameter("promotePrice"));
        int stock = Integer.parseInt(req.getParameter("stock"));
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setSubTitle(subTitle);
        product.setCategory(categoryService.get(cid));
        product.setOriginalPrice(originalPrice);
        product.setPromotePrice(promotePrice);
        product.setStock(stock);
        productService.update(product);
        return "@/admin_product_list?id="+cid;
    }
}
