package com.iweb.controller.admin;

import com.iweb.entity.Product;
import com.iweb.entity.ProductImage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/7 19:39
 */
@WebServlet("/productImageServlet")
public class ProductImageServlet extends BaseBackServlet {
    public String list(HttpServletRequest req, HttpServletResponse resp){
        int pid = Integer.parseInt(req.getParameter("id"));
        Product product = productService.get(pid);
        List<ProductImage> piList = productImageService.list(pid);
        req.setAttribute("piList",piList);
        req.setAttribute("product",product);
        return "/page/admin/productImage/listImage.jsp";
    }

    public String add(HttpServletRequest req,HttpServletResponse resp){
        int pid = Integer.parseInt(req.getParameter("pid"));
        String piUrl = req.getParameter("piUrl");
        ProductImage pi = new ProductImage();
        pi.setUrl(piUrl);
        pi.setP(productService.get(pid));
        productImageService.add(pi);
        return "@/admin_productImage_list?id="+pid;
    }

    public String delete(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        productImageService.delete(id);
        return "%success";
    }
}
