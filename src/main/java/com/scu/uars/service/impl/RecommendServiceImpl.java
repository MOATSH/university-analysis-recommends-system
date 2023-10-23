package com.scu.uars.service.impl;

import com.scu.uars.bean.UnivEnrollment;
import com.scu.uars.bean.UnivInfo;
import com.scu.uars.dao.RecommendDao;
import com.scu.uars.dao.UnivDao;
import com.scu.uars.dao.impl.RecommendDaoImpl;
import com.scu.uars.dao.impl.UnivDaoImpl;
import com.scu.uars.service.RecommendService;

import java.util.List;
import java.util.Objects;

/**
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-18 11:14
 **/

public class RecommendServiceImpl implements RecommendService {
    RecommendDao recommendDao = new RecommendDaoImpl();
    UnivDao univDao = new UnivDaoImpl();

    @Override
    public List<UnivInfo> queryUnivInfo(String univ_province, String user_province, String major) {
        if (univ_province.equals("无") && major.length() == 0) {
            return recommendDao.queryUnivInfo(user_province);
        }
        else if(major.length() == 0) {
            return recommendDao.queryUnivInfoByProv(univ_province, user_province);
        }
        else if (univ_province.equals("无")){
            return recommendDao.queryUnivInfoByMajor(major, user_province);
        }
        else {
            return recommendDao.queryUnivInfoByMajorAndProv(major, univ_province, user_province);
        }
    }

    @Override
    public Integer getMinScore(String univ_province, String user_province, String major, String univ_name) {
        List<UnivEnrollment> univEnrollments = getUnivEnrollment(univ_province, user_province, major);
        UnivInfo univInfo = univDao.queryUnivByName(univ_name);

        int min = 1000;
        for (UnivEnrollment univEnrollment: univEnrollments) {
            if (univEnrollment.getLowest_score() != null && univEnrollment.getLowest_score() < min && Objects.equals(univEnrollment.getUniv_id(), univInfo.getUniv_id())) {
                min = univEnrollment.getLowest_score();
            }
        }

        return min;
    }

    @Override
    public Integer getMaxScore(String univ_province, String user_province, String major, String univ_name) {
        List<UnivEnrollment> univEnrollments = getUnivEnrollment(univ_province, user_province, major);
        UnivInfo univInfo = univDao.queryUnivByName(univ_name);

        int max = 0;
        for (UnivEnrollment univEnrollment: univEnrollments) {
            if (univEnrollment.getLowest_score() != null && univEnrollment.getLowest_score() > max && Objects.equals(univEnrollment.getUniv_id(), univInfo.getUniv_id())) {
                max = univEnrollment.getLowest_score();
            }
        }

        return max;
    }

    private List<UnivEnrollment> getUnivEnrollment(String univ_province, String user_province, String major) {
        List<UnivEnrollment> univEnrollments;

        if (univ_province.equals("无") && major.length() == 0) {
            univEnrollments = recommendDao.queryUnivEnrollment(user_province);
        }
        else if(major.length() == 0) {
            univEnrollments = recommendDao.queryUnivEnrollmentByProv(univ_province, user_province);
        }
        else if (univ_province.equals("无")){
            univEnrollments = recommendDao.queryUnivEnrollmentByMajor(major, user_province);
        }
        else {
            univEnrollments = recommendDao.queryUnivEnrollmentByMajorAndProv(major, univ_province, user_province);
        }
        return univEnrollments;
    }
}
