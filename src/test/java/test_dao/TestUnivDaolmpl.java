package test_dao;
import com.scu.uars.bean.BeanTest;
import com.scu.uars.bean.GradQuality;
import com.scu.uars.bean.UnivEnrollment;
import com.scu.uars.bean.UnivInfo;
import com.scu.uars.dao.UnivDao;
import com.scu.uars.dao.impl.BaseDao;
import com.scu.uars.dao.impl.UnivDaoImpl;
import org.junit.Test;

import java.util.List;

/**
 * 测试类，测试UnivDaoImpl
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-17 10:49
 **/

public class TestUnivDaolmpl {
    private UnivDaoImpl univDao = new UnivDaoImpl();


    @Test
    public void testQueryUnivByProvince(){
        String province = "四川";
        List<UnivInfo> univInfos= univDao.queryUnivByProvince(province);
        System.out.println(univInfos);

    }

    @Test
    public void testTopUniv(){
        univDao.topUniv();
        List<UnivInfo> UnivInfos = univDao.topUniv();

        System.out.println(UnivInfos);

    }

    @Test
    public void testQueryUnivByName(){
        String univName = "四川大学";
        UnivInfo univInfo=univDao.queryUnivByName(univName);

        System.out.println(univInfo);

    }

    @Test
    public void testAddFavor(){
        String username = "Asuka";
        String univName = "四川大学";
        int ret = univDao.addFavor(username,univName);

        System.out.println(ret);

    }

    @Test
    public void testQueryEnrollmentByName(){
        String univName = "四川大学";
        List<UnivEnrollment> univEnrollments = univDao.queryEnrollmentByName(univName);

        System.out.println(univEnrollments);

    }

    @Test
    public void testQueryGradEqualityByName(){
        String univName = "四川大学";
        GradQuality gradQuality = univDao.queryGradEqualityByName(univName);

        System.out.println(gradQuality);

    }

    @Test
    public void testQueryAllUnivName(){
        List<String> strings = univDao.queryAllUnivName();

        System.out.println(strings);

    }

}
