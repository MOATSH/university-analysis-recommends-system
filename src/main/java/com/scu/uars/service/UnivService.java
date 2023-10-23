package com.scu.uars.service;

import com.scu.uars.bean.GradQuality;
import com.scu.uars.bean.UnivEnrollment;
import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;

import java.util.List;

public interface UnivService {
    /**
     * 获取前10学校
     * @return List<UnivInfo>
     */
    public List<UnivInfo> topUniv();

    /**
     * 根据用户名获取用户实例
     * @return User
     */
    public User getUserByName(String username);

    /**
     * 获取到对应省份的所有大学
     * @return List<UnivInfo>
     */
    public List<UnivInfo> getUnivByProvince(String provName);

    /**
     * 获取所有的院校名称
     * @return List<UnivInfo>
     */
    public List<String> getAllUnivName();

    /**
     * 根据院校名称获取院校信息
     * @param name name
     * @return UnivInfo
     */
    public UnivInfo getUnivInfoByName(String name);

    /**
     * 根据院校名称获取招生信息
     * @param name name
     * @return List<UnivEnrollment>
     */
    public List<UnivEnrollment> getUnivEnrollmentByName(String name);

    /**
     * 根据院校名称获取院校毕业生质量信息
     * @param name name
     */
    public GradQuality getGradQualityByName(String name);

    /**
     * 添加意向院校
     * @param username username
     * @param univName univName
     * @return boolean
     */
    public boolean addFavourUniv(String username, String univName);

    /**
     * 根据院校名称和招生年份获取招生信息
     */
    public List<UnivEnrollment> getUnivEnrollmentByNameAndYear(String univName, int year);
}
