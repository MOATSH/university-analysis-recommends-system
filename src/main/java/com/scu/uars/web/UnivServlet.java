package com.scu.uars.web;

import com.scu.uars.bean.GradQuality;
import com.scu.uars.bean.UnivEnrollment;
import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;
import com.scu.uars.service.UnivService;
import com.scu.uars.service.UserService;
import com.scu.uars.service.impl.UnivServiceImpl;
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
 * @Create: 2023-07-16 13:34
 **/

@WebServlet(urlPatterns = {
        "/univService"
})
public class UnivServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();
    private UnivService univService = new UnivServiceImpl();


    protected void home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.topUniv(req, resp);
        /*
        将用户名cookie转化为User对象
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                User user = univService.getUserByName(cookie.getValue());
                req.setAttribute("user", user);
            }
        }
        */

        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }

    protected void topUniv(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UnivInfo> topUnivInfos = univService.topUniv();

        req.setAttribute("topUniv", topUnivInfos);
    }

    protected void displayUnivByProvince(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String provName = req.getParameter("prov_name");

        List<UnivInfo> provUnivs = univService.getUnivByProvince(provName);
        req.setAttribute("provUnivs", provUnivs);
        req.setAttribute("prov_name", provName);

        req.getRequestDispatcher("/univService?action=home").forward(req, resp);
    }

    protected void analyseHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getAttribute("univGrad1") == null || req.getAttribute("univGrad2") == null) {
            UnivInfo univInfo1 = univService.getUnivInfoByName("四川大学");
            UnivInfo univInfo2 = univService.getUnivInfoByName("清华大学");
            req.setAttribute("univInfo1", univInfo1);
            req.setAttribute("univInfo2", univInfo2);

            GradQuality univGrad1 = univService.getGradQualityByName("四川大学");
            GradQuality univGrad2 = univService.getGradQualityByName("清华大学");
            req.setAttribute("univGrad1", univGrad1);
            req.setAttribute("univGrad2", univGrad2);
        }

        req.getRequestDispatcher("/WEB-INF/analyse.jsp").forward(req, resp);
    }

    protected void analyse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String univName1 = req.getParameter("univName1");
        String univName2 = req.getParameter("univName2");
        req.setAttribute("univName1", univName1);
        req.setAttribute("univName2", univName2);

        //查询数据库，设置域变量
        //院校信息
        UnivInfo univInfo1 = univService.getUnivInfoByName(univName1);
        UnivInfo univInfo2 = univService.getUnivInfoByName(univName2);
        req.setAttribute("univInfo1", univInfo1);
        req.setAttribute("univInfo2", univInfo2);
        //院校毕业生信息
        GradQuality univGrad1 = univService.getGradQualityByName(univName1);
        GradQuality univGrad2 = univService.getGradQualityByName(univName2);
        req.setAttribute("univGrad1", univGrad1);
        req.setAttribute("univGrad2", univGrad2);


        req.getRequestDispatcher("/univService?action=analyseHome").forward(req, resp);
    }

    protected void univInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> allUnivName = univService.getAllUnivName();
        req.setAttribute("allUnivName", allUnivName);

        if (req.getAttribute("univInfoByName") == null) {
            UnivInfo univInfoByName = univService.getUnivInfoByName("北京大学");
            GradQuality gradQualityByName = univService.getGradQualityByName("北京大学");
            gradQualityByName.fix();

            req.setAttribute("univInfoByName", univInfoByName);
            req.setAttribute("gradQualityByName", gradQualityByName);
        }

        if (req.getAttribute("year") == null) {
            int year = 2023;

            req.setAttribute("year", year);
            List<UnivEnrollment> univEnrollmentsByNameAndYear = univService.getUnivEnrollmentByNameAndYear("北京大学", year);
            req.setAttribute("univEnrollmentsByNameAndYear", univEnrollmentsByNameAndYear);
        }

        req.getRequestDispatcher("/WEB-INF/info.jsp").forward(req, resp);
    }

    protected void displayUnivInfoByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String univName = req.getParameter("univName");
        int year = WebUtils.parseInt(req.getParameter("year"), 2023);

        UnivInfo univInfoByName = univService.getUnivInfoByName(univName);
        GradQuality gradQualityByName = univService.getGradQualityByName(univName);
        gradQualityByName.fix();
        List<UnivEnrollment> univEnrollmentsByNameAndYear = univService.getUnivEnrollmentByNameAndYear(univName, year);

        req.setAttribute("year", year);
        req.setAttribute("univEnrollmentsByNameAndYear", univEnrollmentsByNameAndYear);
        req.setAttribute("univInfoByName", univInfoByName);
        req.setAttribute("gradQualityByName", gradQualityByName);

        req.getRequestDispatcher("/univService?action=univInfo").forward(req, resp);
    }

    protected void addFavour(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        User user = new User();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                user = userService.getUserByName(cookie.getValue());
                req.setAttribute("user", user);
            }
        }

        String univName = req.getParameter("univName");
        req.setAttribute("univName", univName);

        univService.addFavourUniv(user.getUsername(), univName);
        req.getRequestDispatcher("/univService?action=displayUnivInfoByName").forward(req, resp);
    }
}
