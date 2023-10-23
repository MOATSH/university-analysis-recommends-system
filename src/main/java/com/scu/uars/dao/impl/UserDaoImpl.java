package com.scu.uars.dao.impl;

import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;
import com.scu.uars.bean.UserFavor;
import com.scu.uars.dao.UserDao;

import java.util.List;

/**
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-15 09:01
 **/

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByName(String userName) {
        String sql = "select * from user where username = ?";

        return this.queryForOne(User.class, sql, userName);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into user(username, password, province) values(?, ?, ?)";

        return this.update(sql, user.getUsername(), user.getPassword(), user.getProvince());
    }

    @Override
    public User queryUserByNameAndPassword(User user) {
        String sql = "select * from user where username = ? and password = ?";

        return this.queryForOne(User.class, sql, user.getUsername(), user.getPassword());
    }

    @Override
    public int updatePassword(User user) {
        String sql = "update user set password = ? where username = ?";

        return this.update(sql, user.getPassword(), user.getUsername());
    }

    @Override
    public int updateProvince(User user) {
        String sql = "update user set province = ? where username = ?";

        return this.update(sql, user.getProvince(), user.getUsername());
    }

    @Override
    public List<UnivInfo> queryFavorUniv(User user) {
        User user1 = this.queryUserByName(user.getUsername());

        String sql = "select * from univ_info where univ_id in (select favor_univ_id as univ_id from user_favor where user_id = ?)";

        return this.queryForList(UnivInfo.class, sql, user1.getId());
    }

    @Override
    public int removeFavorUniv(Integer userId, Integer univId) {
        String sql = "delete from user_favor where user_id = ? and favor_univ_id = ?";

        return this.update(sql, userId, univId);
    }

    @Override
    public int queryUnivId(String univName) {
        String sql = "select univ_id from univ_info where univ_name = ?";

        return (int) this.queryForSingle(sql, Integer.class, univName);
    }

    @Override
    public List<User> queryAllUsers() {
        String sql = "select * from user";

        return this.queryForList(User.class, sql);
    }

    @Override
    public boolean deleteUser(String username) {
        String sql = "delete from user where username = ?";

        return (this.update(sql, username) > 0);
    }
}
