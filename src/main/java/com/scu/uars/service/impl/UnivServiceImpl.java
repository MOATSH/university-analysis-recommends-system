package com.scu.uars.service.impl;

import com.scu.uars.bean.GradQuality;
import com.scu.uars.bean.UnivEnrollment;
import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;
import com.scu.uars.dao.UnivDao;
import com.scu.uars.dao.UserDao;
import com.scu.uars.dao.impl.UnivDaoImpl;
import com.scu.uars.dao.impl.UserDaoImpl;
import com.scu.uars.service.UnivService;

import java.util.List;

/**
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-16 13:35
 **/

public class UnivServiceImpl implements UnivService {
    private UnivDao univDao = new UnivDaoImpl();
    private UserDao userDao = new UserDaoImpl();


    @Override
    public List<UnivInfo> topUniv() {
        List<UnivInfo> univInfos = univDao.topUniv();

        if (univInfos.size() > 10) {
            return univInfos.subList(0, 10);
        }

        return univInfos;
    }

    @Override
    public User getUserByName(String username) {
        return userDao.queryUserByName(username);
    }

    @Override
    public List<UnivInfo> getUnivByProvince(String provName) {
        return univDao.queryUnivByProvince(provName);
    }

    @Override
    public List<String> getAllUnivName() {
        return univDao.queryAllUnivName();
    }

    @Override
    public UnivInfo getUnivInfoByName(String name) {
        return univDao.queryUnivByName(name);
    }

    @Override
    public List<UnivEnrollment> getUnivEnrollmentByName(String name) {
        return univDao.queryEnrollmentByName(name);
    }

    @Override
    public GradQuality getGradQualityByName(String name) {
        return univDao.queryGradEqualityByName(name);
    }

    @Override
    public boolean addFavourUniv(String username, String univName) {
        return (univDao.addFavor(username, univName) > 0);
    }

    @Override
    public List<UnivEnrollment> getUnivEnrollmentByNameAndYear(String univName, int year) {
        return univDao.queryEnrollmentByNameAndYear(univName, year);
    }
}
