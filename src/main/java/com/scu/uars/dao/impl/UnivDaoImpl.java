package com.scu.uars.dao.impl;

import com.scu.uars.bean.GradQuality;
import com.scu.uars.bean.UnivEnrollment;
import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;
import com.scu.uars.dao.UnivDao;
import com.scu.uars.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-16 10:15
 **/

public class UnivDaoImpl extends BaseDao implements UnivDao {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<UnivInfo> queryUnivByProvince(String province) {
        String sql = "select * from univ_info where prov_name = ?";

        return this.queryForList(UnivInfo.class, sql, province);
    }

    @Override
    public List<UnivInfo> topUniv() {
        String sql = "select count(*), favor_univ_id as univ_id from user_favor group by favor_univ_id order by count(*) desc";

        List<UnivInfo> univInfos = this.queryForList(UnivInfo.class, sql);
        List<UnivInfo> result = new ArrayList<UnivInfo>();
        for (UnivInfo univInfo: univInfos) {
            String sql1 = "select * from univ_info where univ_id = ?";
            result.add(this.queryForOne(UnivInfo.class, sql1, univInfo.getUniv_id()));
        }

        return result;
    }

    @Override
    public UnivInfo queryUnivByName(String univName) {
        String sql = "select * from univ_info where univ_name = ?";

        return this.queryForOne(UnivInfo.class, sql, univName);
    }

    @Override
    public int addFavor(String username, String univName) {
        UnivInfo univInfo = this.queryUnivByName(univName);
        User user = userDao.queryUserByName(username);

        String sql = "insert into user_favor values(?, ?)";

        return this.update(sql, user.getId(), univInfo.getUniv_id());
    }

    @Override
    public List<UnivEnrollment> queryEnrollmentByName(String univName) {
        UnivInfo univInfo = this.queryUnivByName(univName);

        String sql = "select * from univ_enrollment where univ_id = ?";

        return this.queryForList(UnivEnrollment.class, sql, univInfo.getUniv_id());
    }

    @Override
    public GradQuality queryGradEqualityByName(String univName) {
        String sql = "select * from grad_quality where univ_name = ?";

        return this.queryForOne(GradQuality.class, sql, univName);
    }

    @Override
    public List<String> queryAllUnivName() {
        String sql = "select univ_name from univ_info";
        List<String> result = new ArrayList<String>();

        List<UnivInfo> univInfos = this.queryForList(UnivInfo.class, sql);
        for (UnivInfo univInfo : univInfos) {
            result.add(univInfo.getUniv_name());
        }

        return result;
    }

    @Override
    public List<UnivEnrollment> queryEnrollmentByNameAndYear(String univName, int year) {
        UnivInfo univInfo = this.queryUnivByName(univName);
        String sql = "select * from univ_enrollment where univ_id = ? and year = ?";

        return this.queryForList(UnivEnrollment.class, sql, univInfo.getUniv_id(), year);
    }
}
