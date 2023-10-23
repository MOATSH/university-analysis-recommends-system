package com.scu.uars.dao.impl;

import com.scu.uars.bean.UnivEnrollment;
import com.scu.uars.bean.UnivInfo;
import com.scu.uars.dao.RecommendDao;

import java.util.List;

/**
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-18 11:15
 **/

public class RecommendDaoImpl extends BaseDao implements RecommendDao {
    @Override
    public List<UnivEnrollment> queryUnivEnrollmentByProv(String univ_province, String user_province) {
        String sql = "select * from univ_enrollment where prov_name = ? and univ_id in (select univ_id from univ_info where prov_name = ?)";

        return this.queryForList(UnivEnrollment.class, sql, user_province, univ_province);
    }

    @Override
    public List<UnivEnrollment> queryUnivEnrollmentByMajor(String major, String user_province) {
        String sql = "select * from univ_enrollment where major_name like '%" + major + "%' and prov_name = ?";

        return this.queryForList(UnivEnrollment.class, sql, user_province);
    }

    @Override
    public List<UnivEnrollment> queryUnivEnrollmentByMajorAndProv(String major, String univ_province, String user_province) {
        String sql = "select * from univ_enrollment where major_name like '%" + major + "%' and prov_name = ? and univ_id in (select univ_id from univ_info where prov_name = ?)";

        return this.queryForList(UnivEnrollment.class, sql, user_province, univ_province);
    }

    @Override
    public List<UnivEnrollment> queryUnivEnrollment(String user_province) {
        String sql = "select * from univ_enrollment where prov_name = ?";

        return this.queryForList(UnivEnrollment.class, sql, user_province);
    }

    @Override
    public List<UnivInfo> queryUnivInfoByProv(String univ_province, String user_province) {
        String sql = "select * from univ_info where prov_name = ? and univ_id in (select univ_id from univ_enrollment where prov_name = ?)";

        return this.queryForList(UnivInfo.class, sql, univ_province, user_province);
    }

    @Override
    public List<UnivInfo> queryUnivInfoByMajor(String major, String user_province) {
        String sql = "select * from univ_info where univ_id in (select univ_id from univ_enrollment where major_name like '%" + major + "%' and prov_name = ?)";

        return this.queryForList(UnivInfo.class, sql, user_province);
    }

    @Override
    public List<UnivInfo> queryUnivInfoByMajorAndProv(String major, String univ_province, String user_province) {
        String sql = "select * from univ_info where prov_name = ? and univ_id in (select univ_id from univ_enrollment where major_name like '%" + major + "%' and prov_name = ?)";

        return this.queryForList(UnivInfo.class, sql, univ_province,user_province);
    }

    @Override
    public List<UnivInfo> queryUnivInfo(String user_province) {
        String sql = "select * from univ_info where univ_id in (select univ_id from univ_enrollment where prov_name = ?)";

        return this.queryForList(UnivInfo.class, sql, user_province);
    }
}
