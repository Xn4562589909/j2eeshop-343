package com.iweb.controller.admin;

import com.iweb.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yang
 * @date 2023/4/3 17:04
 */
@WebServlet("/userServlet")
public class UserServlet extends BaseBackServlet {
    public String login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 接收参数并封装
        User user = new User();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        user.setName(username);
        user.setPassword(password);
        // 调用service进行处理
        boolean isLogin = userService.login(user);
        // 如果登录成功 应该将用户信息存入session 用于后续校验
        if (isLogin){
            req.getSession().setAttribute("user",user);
            return "@/admin_category_list";
        }else {
            return "@/page/admin/user/login.jsp";
        }
    }

    public String exitLogin(HttpServletRequest req,HttpServletResponse resp){
        // 获取session 并且清除session中的用户信息
        req.getSession().removeAttribute("user");
        // 重新回到登录页面
        return "@/page/admin/user/login.jsp";
    }
}
