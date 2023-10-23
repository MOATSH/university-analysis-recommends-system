package test_dao;

import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;
import com.scu.uars.dao.UserDao;
import com.scu.uars.dao.impl.UserDaoImpl;
import org.junit.Test;

import java.util.List;

/**
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-16 14:28
 **/

public class TestUserDaolmpl {
    private UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void testQueryUserByName() {
        String userName = "moatsh";
        User user = userDao.queryUserByName(userName);

        System.out.println(user);
    }

    @Test
    public void testSaveUser(){
        User user = new User(1,"Aya","114514","四川");
        int ret = userDao.saveUser(user);

        System.out.println(ret);

    }

    @Test
    public void testQueryUserByNameAndPassword(){
        User user = new User(0,"","114514","四川");
        User user1 = userDao.queryUserByNameAndPassword(user);

        System.out.println(user1);

    }

    @Test
    public void testUpdatePassword(){
        User user = new User(1,"Aya","893893","四川");
        int ret = userDao.updatePassword(user);

        System.out.println(ret);

    }

    @Test
    public void testUpdateProvince(){
        User user = new User(1,"Aya","893893","广西");
        int ret = userDao.updateProvince(user);

        System.out.println(ret);

    }

    @Test
    public void testQueryFavorUniv(){
        User user = new User(1,"Aya","893893","广西");
        List<UnivInfo> univInfos = userDao.queryFavorUniv(user);

        System.out.println(univInfos);
    }

    @Test
    public void testRemoveFavorUniv(){
        Integer userId = 1;
        Integer univId = 10610;
        int ret = userDao.removeFavorUniv(userId, univId);

        System.out.println(ret);

    }

    @Test
    public void testQueryUnivId(){
        String univName = "四川大学";
        int ret = userDao.queryUnivId(univName);

        System.out.println(ret);

    }

}
