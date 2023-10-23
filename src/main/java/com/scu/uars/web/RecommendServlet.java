package com.scu.uars.web;

import com.scu.uars.bean.MinAndMaxScore;
import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;
import com.scu.uars.service.RecommendService;
import com.scu.uars.service.UserService;
import com.scu.uars.service.impl.RecommendServiceImpl;
import com.scu.uars.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-18 11:14
 **/

@WebServlet(urlPatterns = {
        "/recommend"
})
public class RecommendServlet extends BaseServlet{
    UserService userService = new UserServiceImpl();
    RecommendService recommendService = new RecommendServiceImpl();


    protected void recommendHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/recommends.jsp").forward(req, resp);
    }

    protected void recommendUniv(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String score = req.getParameter("score");
        String univProvince = req.getParameter("univ_province");
        String major = req.getParameter("major");
        req.setAttribute("score", score);
        req.setAttribute("major", major);
        req.setAttribute("univ_province", univProvince);

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                User user = userService.getUserByName(cookie.getValue());
                List<UnivInfo> univInfos = recommendService.queryUnivInfo(univProvince, user.getProvince(), major);
                List<MinAndMaxScore> minAndMaxScores = new ArrayList<>();

                for (UnivInfo univInfo: univInfos) {
                    Integer minScore = recommendService.getMinScore(univProvince, user.getProvince(), major, univInfo.getUniv_name());
                    Integer maxScore = recommendService.getMaxScore(univProvince, user.getProvince(), major, univInfo.getUniv_name());

                    minAndMaxScores.add(new MinAndMaxScore(minScore, maxScore));
                }

                req.setAttribute("univInfos", univInfos);
                req.setAttribute("min_max_scores", minAndMaxScores);
            }
        }

        req.getRequestDispatcher("/recommend?action=recommendHome").forward(req, resp);
    }
}
