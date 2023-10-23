package com.scu.uars.dao;

import com.scu.uars.bean.UnivEnrollment;
import com.scu.uars.bean.UnivInfo;

import java.util.List;

public interface RecommendDao {
    /**
     * 根据省份查找招生信息
     * @param univ_province 大学所在省份
     * @return list<UnivEnrollment>
     */
    public List<UnivEnrollment> queryUnivEnrollmentByProv(String univ_province, String user_province);

    /**
     * 根据专业名称查找招生信息
     * @param major major
     * @return list<UnivEnrollment>
     */
    public List<UnivEnrollment> queryUnivEnrollmentByMajor(String major, String user_province);

    /**
     * 根据院校所在省份和专业查找招生信息
     * @param major major
     * @param univ_province 院校所在省份
     * @param user_province 用户所在省份
     * @return List<UnivEnrollment>
     */
    public List<UnivEnrollment> queryUnivEnrollmentByMajorAndProv(String major, String univ_province, String user_province);

    /**
     * 只根据用户所在省份查找招生信息
     * @return List<UnivEnrollment>
     */
    public List<UnivEnrollment> queryUnivEnrollment(String user_province);

    /*
    四个对应的查找院校信息的接口
     */
    public List<UnivInfo> queryUnivInfoByProv(String univ_province, String user_province);

    public List<UnivInfo> queryUnivInfoByMajor(String major, String user_province);

    public List<UnivInfo> queryUnivInfoByMajorAndProv(String major, String univ_province, String user_province);

    public List<UnivInfo> queryUnivInfo(String user_province);
}
