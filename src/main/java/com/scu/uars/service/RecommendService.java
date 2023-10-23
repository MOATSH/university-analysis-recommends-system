package com.scu.uars.service;

import com.scu.uars.bean.UnivEnrollment;
import com.scu.uars.bean.UnivInfo;

import java.util.List;

public interface RecommendService {
    /**
     * 查询高校信息
     * @return List<UnivInfo>
     */
    public List<UnivInfo> queryUnivInfo(String univ_province, String user_province, String major);

    /**
     * 查询高校招生信息最低分
     * @return Integer
     */
    public Integer getMinScore(String univ_province, String user_province, String major, String uiv_name);

    /**
     * 查询高校招生信息最高分
     * @return Integer
     */
    public Integer getMaxScore(String univ_province, String user_province, String major, String univName);
}
