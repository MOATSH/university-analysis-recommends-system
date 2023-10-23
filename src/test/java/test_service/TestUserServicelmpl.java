package test_service;

import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;
import com.scu.uars.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

public class TestUserServicelmpl {
    private UserServiceImpl userService = new UserServiceImpl();


    @Test
    public void testRegisterUser() {
        User user = new User(1,"Ayanami","893893","广西");
        int ret = userService.registerUser(user);

        System.out.println(ret);

    }

    @Test
    public void testLogin() {
        User user = new User(1,"Ayanami","893893","广西");
        User user1 = userService.login(user);

        System.out.println(user1);

    }

    @Test
    public void testExistsUserName() {
        String username = "Ayanami";
        boolean ret = userService.existsUserName(username);

        System.out.println(ret);

    }

    @Test
    public void testUpdateUserInfo() {
        User user = new User(1,"Ayanami","893893","广西");
        boolean ret = userService.updateUserInfo(user);

        System.out.println(ret);

    }

    @Test
    public void testFavorUniv() {
        User user = new User(1,"Ayanami","893893","广西");
        List<UnivInfo> univInfos = userService.favorUniv(user);

        System.out.println(univInfos);

    }

    @Test
    public void testRemoveFavorUniv() {
        String username = "Aya";
        String univname = "四川大学";

        int ret = userService.removeFavorUniv(username,univname);

        System.out.println(ret);

    }

    @Test
    public void testGetUserByName() {
        String username = "Aya";
        User user = userService.getUserByName(username);

        System.out.println(user);

    }
}
