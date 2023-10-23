package com.scu.uars.service.impl;

import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;
import com.scu.uars.dao.UnivDao;
import com.scu.uars.dao.UserDao;
import com.scu.uars.dao.impl.UnivDaoImpl;
import com.scu.uars.dao.impl.UserDaoImpl;
import com.scu.uars.service.UserService;

import java.util.List;

/**
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-15 09:39
 **/

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    private UnivDao univDao = new UnivDaoImpl();


    @Override
    public int registerUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByNameAndPassword(user);
    }

    @Override
    public boolean existsUserName(String userName) {
        return (userDao.queryUserByName(userName) != null);
    }

    @Override
    public boolean updateUserInfo(User user) {
        User user1 = userDao.queryUserByName(user.getUsername());
        int i = 1;
        int j = 1;

        if (!user1.getPassword().equals(user.getPassword()) && user.getPassword() != null) {
            i = userDao.updatePassword(user);
        }
        if (!user1.getProvince().equals(user.getProvince()) && user.getProvince() != null) {
            j = userDao.updateProvince(user);
        }

        return i > 0 && j > 0;
    }

    @Override
    public List<UnivInfo> favorUniv(User user) {
        return userDao.queryFavorUniv(user);
    }

    @Override
    public int removeFavorUniv( String username, String univName) {
        int univId = userDao.queryUnivId(univName);
        User user = userDao.queryUserByName(username);

        return userDao.removeFavorUniv(user.getId(), univId);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.queryUserByName(username);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDao.queryAllUsers();
        users.removeIf(user -> user.getUsername().equals("admin"));
        return users;
    }

    @Override
    public boolean deleteUser(String username) {
        return userDao.deleteUser(username);
    }

    @Override
    public boolean modifyUser(String username, String newUserName, String newPassword) {
        User user = userDao.queryUserByName(username);
        User newUser = new User(user.getId(), newUserName, newPassword, user.getProvince());
        List<UnivInfo> univInfos = userDao.queryFavorUniv(user);
        boolean b = userDao.deleteUser(username);
        boolean ret = ((userDao.saveUser(newUser) > 0) && b);

        for (UnivInfo univInfo : univInfos) {
            univDao.addFavor(newUser.getUsername(), univInfo.getUniv_name());
        }

        return ret;
    }
}
