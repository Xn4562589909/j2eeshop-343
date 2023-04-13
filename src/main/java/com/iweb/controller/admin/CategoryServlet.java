package com.iweb.controller.admin;

import com.iweb.entity.Category;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/3 17:35
 */
@WebServlet("/categoryServlet")
public class CategoryServlet extends BaseBackServlet {
    public String list(HttpServletRequest req, HttpServletResponse resp){
        // 调用service获取集合
        List<Category> categories = categoryService.list();
        // 将获取到的集合存入到请求中
        req.setAttribute("categories",categories);
        // 通过转发跳转到对应的jsp页面 页面通过el表达式将数据渲染解析过来
        return "/page/admin/category/listCategory.jsp";
    }
    public String add(HttpServletRequest request, HttpServletResponse resp) {
        //获取表单参数
        String name = request.getParameter("name");
        //参数封装到对象中
        Category category = new Category();
        category.setName(name);
        //调用service
        categoryService.add(category);
        //重新发送/admin_category_list的请求
        return "@/admin_category_list";
    }

    public String edit(HttpServletRequest request, HttpServletResponse resp) {
        //获取超链接传递过来的参数
        Integer id = Integer.parseInt(request.getParameter("id"));
        //获取对应分类对象
        Category category = categoryService.edit(id);
        //数据存入请求
        request.setAttribute("c",category);
        //转发跳转页面
        return "/page/admin/category/editCategory.jsp";

    }
    public String update(HttpServletRequest request, HttpServletResponse resp){
        //从编辑界面获取表单参数 并将获取的参数封装到一个实体类对象中
        Category category = new Category();
        category.setId(Integer.parseInt(request.getParameter("id")));
        category.setName(request.getParameter("name"));
        //调用service处理
        categoryService.update(category);
        //重新发送请求 获取最新的listCategory.jsp页面
        return "@/admin_category_list";
    }
    public String delete(HttpServletRequest request, HttpServletResponse resp){
        //获取ajax请求中所携带的参数id
        Integer id = Integer.parseInt(request.getParameter("id"));
        //调用service实现删除
        categoryService.delete(id);
        // 返回响应字符串
        return "%success";
    }
}
