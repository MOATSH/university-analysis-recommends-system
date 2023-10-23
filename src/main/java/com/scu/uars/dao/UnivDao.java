package com.scu.uars.dao;

import com.scu.uars.bean.GradQuality;
import com.scu.uars.bean.UnivEnrollment;
import com.scu.uars.bean.UnivInfo;

import java.util.List;

public interface UnivDao {
    /**
     * 根据省份查找大学
     * @return Lis<UnivInfo>
     */
    public List<UnivInfo> queryUnivByProvince(String province);

    /**
     * 查找top10的大学
     * @return List<UnivInfo>
     */
    public List<UnivInfo> topUniv();

    /**
     * 根据院校名称查找院校
     * @return UnivInfo
     */
    public UnivInfo queryUnivByName(String univName);

    /**
     * 添加意向院校
     * @param username username
     * @param univName univName
     * @return int
     */
    public int addFavor(String username, String univName);

    /**
     * 根据院校名称查找招生信息
     * @param univName univName
     */
    public List<UnivEnrollment> queryEnrollmentByName(String univName);

    /**
     * 根据院校名称查找毕业生质量信息
     * @param univName univName
     */
    public GradQuality queryGradEqualityByName(String univName);

    /**
     * 查找所有的院校名称
     * @return List<UnivInfo></UnivInfo>
     */
    public List<String> queryAllUnivName();

    /**
     * 根据院校名称和年份查找招生信息
     * @param univName univName
     * @param year year
     * @return List<UnivEnrollment>
     */
    public List<UnivEnrollment> queryEnrollmentByNameAndYear(String univName, int year);
}
