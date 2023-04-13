package com.iweb.controller.admin;

import com.iweb.entity.Category;
import com.iweb.entity.Product;
import com.iweb.entity.Property;
import com.iweb.entity.PropertyValue;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/4 2:21
 */
@WebServlet("/propertyValueServlet")
public class PropertyValueServlet extends BaseBackServlet {
    public String list(HttpServletRequest req, HttpServletResponse resp){
        int pid = Integer.parseInt(req.getParameter("id"));
        Product product = productService.get(pid);
        Category category = product.getCategory();
        List<Property> properties = propertyService.list(category.getId());
        List<PropertyValue> propertyValues = propertyValueService.list(pid);
        properties = propertyValueService.listNotAddPt(properties,propertyValues);
        req.setAttribute("pts",properties);
        req.setAttribute("pvs",propertyValues);
        req.setAttribute("product",product);
        return "/page/admin/propertyValue/listPropertyValue.jsp";
    }

    public String update(HttpServletRequest req,HttpServletResponse resp){
        int pvid = Integer.parseInt(req.getParameter("id"));
        String newValue = req.getParameter("value");
        PropertyValue pv = propertyValueService.get(pvid);
        pv.setValue(newValue);
        propertyValueService.update(pv);
        return "%success";
    }

    public String add(HttpServletRequest req,HttpServletResponse resp){
        int pid = Integer.parseInt(req.getParameter("pid"));
        String ptIdName = req.getParameter("ptName");
        String value = req.getParameter("value");
        int ptId = Integer.parseInt(ptIdName.substring(0,ptIdName.indexOf("-")));
        Property property = propertyService.get(ptId);
        PropertyValue pv = new PropertyValue();
        pv.setValue(value);
        pv.setProperty(property);
        pv.setProduct(productService.get(pid));
        propertyValueService.add(pv);
        return "@/admin_propertyValue_list?id="+pid;
    }

    public String delete(HttpServletRequest req,HttpServletResponse resp){
        String pvIdName = req.getParameter("pvName");
        int pvId = Integer.parseInt(pvIdName.substring(0,pvIdName.indexOf("-")));
        Product product = propertyValueService.get(pvId).getProduct();
        propertyValueService.delete(pvId);
        return "@/admin_propertyValue_list?id="+product.getId();
    }
}
