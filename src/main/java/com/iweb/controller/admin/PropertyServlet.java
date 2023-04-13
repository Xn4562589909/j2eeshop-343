package com.iweb.controller.admin;

import com.iweb.DAO.impl.CategoryDAOImpl;
import com.iweb.entity.Category;
import com.iweb.entity.Property;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/3 21:39
 */
@WebServlet("/propertyServlet")
public class PropertyServlet extends BaseBackServlet {
    public String list(HttpServletRequest request, HttpServletResponse resp) {
        //接受参数 获取指定分类的所有属性
        Integer cid = Integer.parseInt(request.getParameter("id"));
        //调用service 获取集合
        List<Property> properties = propertyService.list(cid);
        if (properties.size() == 0){
            return "/page/admin/property/nullProperty.html";
        }
        //将获取到的集合存入到请求中
        request.setAttribute("pts", properties);
        //通过转发跳转到对应的jsp页面 页面通过el表达式将数据渲染解析出来
        return "/page/admin/property/listProperty.jsp";
    }

    public String add(HttpServletRequest request, HttpServletResponse resp) {
        //获取表单参数
        String name = request.getParameter("name");
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        //获取对应的分类对象
        Category category = new CategoryDAOImpl().get(cid);
        //参数封装到对象中
        Property property = new Property();
        property.setName(name);
        property.setCategory(category);
        //调用service
        propertyService.add(property);
        //重新发送/admin_category_list的请求
        return "@/admin_property_list?id="+cid;
    }

    public String edit(HttpServletRequest request, HttpServletResponse resp) {
        //获取超链接传递过来的参数
        Integer id = Integer.parseInt(request.getParameter("id"));
        //获取对应属性对象
        Property property = propertyService.edit(id);
        //获取属性对应分类id
        int cid = property.getCategory().getId();
        //数据存入请求
        request.setAttribute("pt",property);
        request.setAttribute("cid",cid);
        //转发跳转页面
        return "/page/admin/property/editProperty.jsp";

    }
    public String update(HttpServletRequest request, HttpServletResponse resp){
        //从编辑界面获取表单参数 并将获取的参数封装到一个实体类对象中
        int id =   Integer.parseInt(request.getParameter("id"));
        int cid = Integer.parseInt(request.getParameter("cid"));
        String name = request.getParameter("name");
        Category category = new Category();
        category.setId(cid);
        Property property = new Property();
        property.setId(id);
        property.setName(name);
        property.setCategory(category);
        //调用service处理
        propertyService.update(property);
        //重新发送请求 获取最新的listCategory.jsp页面
        return "@/admin_property_list?id="+cid;
    }
    public String delete(HttpServletRequest request, HttpServletResponse resp){
        //获取ajax请求中所携带的参数id
        Integer id = Integer.parseInt(request.getParameter("id"));
        //调用service实现删除
        propertyService.delete(id);
        // 返回响应字符串
        return "%success";
    }

}
