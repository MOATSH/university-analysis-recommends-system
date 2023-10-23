package com.scu.uars.web;

import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;
import com.scu.uars.service.UserService;
import com.scu.uars.service.impl.UserServiceImpl;
import com.scu.uars.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-15 10:01
 **/

@WebServlet(urlPatterns = {
        "/userServlet"
})
public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();


    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());


        if (userService.login(user) != null && user.getUsername().equals("admin")) {
            managerHome(req, resp);
        } else if (userService.login(user) != null) {
            //设置用户名cookie
            Cookie cookie = new Cookie("username", user.getUsername());
            resp.setContentType("text/html; charset=utf-8");
            cookie.setMaxAge(3600*24);
            resp.addCookie(cookie);

            req.getRequestDispatcher("/univService?action=home").forward(req, resp);
        } else{
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("password", user.getPassword());

            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //检查用户名是否可用
        if (userService.existsUserName(user.getUsername())) {
            req.setAttribute("msg", "用户名已存在");
            req.setAttribute("password", user.getPassword());

            //跳回注册页面
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
        else {
            //设置用户名cookie
            Cookie cookie = new Cookie("username", user.getUsername());
            resp.setContentType("text/html; charset=utf-8");
            cookie.setMaxAge(3600*24);
            resp.addCookie(cookie);

            userService.registerUser(user);
            //跳回首页
            req.getRequestDispatcher("/univService?action=home").forward(req, resp);
        }
    }

    protected void userInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                User user = userService.getUserByName(cookie.getValue());
                List<UnivInfo> favorUnivs = userService.favorUniv(user);
                req.setAttribute("user", user);
                req.setAttribute("favorUnivs", favorUnivs);
            }
        }



        req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
    }

    protected void updateInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        boolean flag = userService.updateUserInfo(user);
        if (flag) {
            req.setAttribute("update", "success");
        }
        else {
            req.setAttribute("update", "failure");
        }


        req.getRequestDispatcher("/userServlet?action=userInfo").forward(req, resp);
    }

    protected void deleteFavourUniv(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String univName = req.getParameter("univ_name");

        userService.removeFavorUniv(username, univName);

        req.getRequestDispatcher("/userServlet?action=userInfo").forward(req, resp);
    }

    protected void managerHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/administrator.jsp").forward(req, resp);
    }

    protected void managerUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = userService.getAllUsers();
        req.setAttribute("users", allUsers);

        req.getRequestDispatcher("/WEB-INF/admin_user.jsp").forward(req, resp);
    }

    protected void managerUniv(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin_university.jsp").forward(req, resp);
    }

    protected void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if (userService.deleteUser(username)) {
            req.setAttribute("delete", "success");
        }
        else {
            req.setAttribute("delete", "failure");
        }

        req.getRequestDispatcher("/userServlet?action=managerUser").forward(req, resp);
    }

    protected void managerUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String newPassword = req.getParameter("newPassword");
        String newUsername = req.getParameter("newUsername");

        boolean modify = userService.modifyUser(username, newUsername, newPassword);

        if (modify) {
            req.setAttribute("modify", "success");
        }
        else {
            req.setAttribute("modify", "failure");
        }

        req.getRequestDispatcher("/userServlet?action=managerUser").forward(req, resp);
    }
}
