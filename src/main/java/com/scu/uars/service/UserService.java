package com.scu.uars.service;

import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;

import java.util.List;

/**
 * UserService User业务处理类
 */

public interface UserService {
    /**
     * 注册用户业务
     * 返回-1表示注册失败
     * @param user User实例
     * @return int
     */
    public int registerUser(User user);

    /**
     * 登录业务
     * 登录成功返回用户实例
     * 登录失败返回null
     * @return User
     */
    public User login(User user);

    /**
     * 查看用户名是否已存在
     * @return boolean
     */
    public boolean existsUserName(String userName);

    /**
     * 用户修改信息业务
     * @return boolean
     */
    public boolean updateUserInfo(User user);

    /**
     * 查找所有意向院校业务
     */
    public List<UnivInfo> favorUniv(User user);

    /**
     * 移除用户的某个意向院校
     * @return int
     */
    public int removeFavorUniv(String username, String univName);

    /**
     * 根据用户名获取用户实例
     * @param username username
     * @return User
     */
    public User getUserByName(String username);

    /**
     * 查找所有的用户
     * @return List<User>
     */
    public List<User> getAllUsers();

    /**
     * 根据用户名删除用户
     * @param username username
     * @return boolean
     */
    public boolean deleteUser(String username);

    /**
     * 修改用户名和密码
     * @return boolean
     */
    public boolean modifyUser(String username, String newUserName, String newPassword);
}
