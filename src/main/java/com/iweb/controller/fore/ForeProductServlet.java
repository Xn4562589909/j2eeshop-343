package com.iweb.controller.fore;

import com.iweb.comparator.ProductPriceComparator;
import com.iweb.comparator.ProductReviewComparator;
import com.iweb.comparator.ProductSaleComparator;
import com.iweb.entity.Category;
import com.iweb.entity.Product;
import com.iweb.entity.PropertyValue;
import com.iweb.entity.Review;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/11 1:30
 */
@WebServlet("/foreProductServlet")
public class ForeProductServlet extends BaseForeServlet {

    public String show(HttpServletRequest req, HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.get(id);
        List<PropertyValue> pvs = propertyValueService.list(id);
        List<Review> reviews = reviewService.list(id);
        req.setAttribute("pvs",pvs);
        req.setAttribute("product",product);
        req.setAttribute("reviews",reviews);
        return "/page/fore/noLogin/showProduct.jsp";
    }

    public String cList(HttpServletRequest req,HttpServletResponse resp){
        int cid = Integer.parseInt(req.getParameter("id"));
        String sortord = req.getParameter("sort");
        Category category = categoryService.get(cid);
        Comparator<Product> comparator;
        if ("price".equals(sortord)){
            comparator = new ProductPriceComparator();
            Collections.sort(category.getProducts(),comparator);
        }else if ("sale".equals(sortord)){
            comparator = new ProductSaleComparator();
            Collections.sort(category.getProducts(),comparator);
        }else {
            comparator = new ProductReviewComparator();
            Collections.sort(category.getProducts(),comparator);
        }
        req.setAttribute("category",category);
        return "/page/fore/noLogin/categoryShowProduct.jsp";
    }

    public String nameList(HttpServletRequest req,HttpServletResponse resp){
        String searchNameProduct = req.getParameter("searchProduct");
        List<Product> products = productService.list(searchNameProduct);
        String sortord = req.getParameter("sort");
        Comparator<Product> comparator;
        if (null != sortord){
            if ("price".equals(sortord)){
                comparator = new ProductPriceComparator();
                Collections.sort(products,comparator);
            }else if ("sale".equals(sortord)){
                comparator = new ProductSaleComparator();
                Collections.sort(products,comparator);
            }else {
                comparator = new ProductReviewComparator();
                Collections.sort(products,comparator);
            }
        }
        req.setAttribute("searchNameProduct",searchNameProduct);
        req.setAttribute("products",products);
        return "/page/fore/noLogin/fuzzySearchProduct.jsp";
    }
}
