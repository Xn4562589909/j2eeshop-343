package com.iweb.controller.fore;

import com.iweb.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yang
 * @date 2023/4/10 3:19
 */
@WebServlet("/foreUserServlet")
public class ForeUserServlet extends BaseForeServlet {
    public String login(HttpServletRequest req,HttpServletResponse resp){
        User user = new User();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        user.setName(username);
        user.setPassword(password);
        // 调用service进行处理
        boolean isLogin = userService.login(user);
        // 如果登录成功 应该将用户信息存入session 用于后续校验
        if (isLogin){
            user = userService.get(user);
            int oiNum = orderItemService.getShoppingCartsNum(user.getId());
            req.getSession().setAttribute("foreUser",user);
            req.getSession().setAttribute("oiNum",oiNum);
            return "@/fore_homePage_list";
        }else {
            return "@/page/fore/noLogin/login.jsp";
        }
    }

    public String exitLogin(HttpServletRequest req, HttpServletResponse resp){
        // 获取session 并且清除session中的用户信息
        req.getSession().removeAttribute("foreUser");
        req.getSession().removeAttribute("oiNum");
        // 重新回到登录页面
        return "@/fore_homePage_list";
    }

    public String register(HttpServletRequest req,HttpServletResponse resp){
        User user = new User();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        user.setName(username);
        user.setPassword(password);
        boolean isRegister = userService.register(user);
        if (isRegister){
            return "@/page/fore/noLogin/login.jsp";
        }else {
            return "@/page/fore/noLogin/register.jsp";
        }
    }

    public String update(HttpServletRequest req,HttpServletResponse response){
        User user = (User) req.getSession().getAttribute("foreUser");
        String updatePassword = req.getParameter("password");
        user.setPassword(updatePassword);
        userService.update(user);
        user = userService.get(user);
        req.getSession().setAttribute("foreUser",user);
        return "@/page/fore/needLogin/user/updateUser.jsp";
    }
}

