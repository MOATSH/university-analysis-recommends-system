package com.scu.uars.dao;

import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;
import com.scu.uars.bean.UserFavor;

import java.util.List;

/**
 * 访问数据库操作表user接口类
 */

public interface UserDao {
    /**
     * 根据用户名查询用户
     * 存在返回user实例
     * 不存在返回null
     * @param userName 用户名
     * @return User
     */
    public User queryUserByName(String userName);

    /**
     * 将用户信息保存到数据库, 返回影响行数
     * 执行失败返回-1
     * @param user user实例
     * @return int
     */
    public int saveUser(User user);

    /**
     * 根据用户名+密码查找用户
     * 存在返回user实例
     * 不存在返回null
     * @param user User实例
     * @return User
     */
    public User queryUserByNameAndPassword(User user);

    /**
     * 修改密码
     * @return int
     */
    public int updatePassword(User user);

    /**
     * 修改省份
     * @return int
     */
    public int updateProvince(User user);

    /**
     * 查找用户的意向院校
     * @param user user
     * @return userFavor
     */
    public List<UnivInfo> queryFavorUniv(User user);

    /**
     * 移除意向院校
     */
    public int removeFavorUniv(Integer userId, Integer univId);

    /**
     * 查找univ_id
     * @return int
     */
    public int queryUnivId(String univName);

    /**
     * 查找所有的用户
     * @return List<User>
     */
    public List<User> queryAllUsers();

    /**
     * 查询数据库删除user
     * @param username username
     * @return boolean
     */
    public boolean deleteUser(String username);
}
