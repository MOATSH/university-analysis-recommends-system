package test_service;

import com.scu.uars.bean.UnivInfo;
import com.scu.uars.bean.User;
import com.scu.uars.service.impl.UnivServiceImpl;
import org.junit.Test;

import java.util.List;


public class TestUnivServicelmpl {
    private UnivServiceImpl univService = new UnivServiceImpl();


    @Test
    public void testTopUniv() {
        List<UnivInfo> univInfos = univService.topUniv();

        System.out.println(univInfos);

    }

    @Test
    public void testGetUserByName() {
        String username = "Aya";
        User user1 = univService.getUserByName(username);

        System.out.println(user1);

    }

    @Test
    public void testGetUnivByProvince() {
        String province = "四川";
        List<UnivInfo> univInfos = univService.getUnivByProvince(province);

        System.out.println(univInfos);

    }

    @Test
    public void testGetAllUnivName(){
        List<String> strings = univService.getAllUnivName();

        System.out.println(strings);

    }

    @Test
    public void testGetUnivInfoByName(){
        String name = "四川大学";
        UnivInfo univInfo = univService.getUnivInfoByName(name);

        System.out.println(univInfo);

    }

}
