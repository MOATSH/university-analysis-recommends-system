package test_dao;

import com.scu.uars.bean.BeanTest;
import com.scu.uars.dao.impl.BaseDao;
import org.junit.Test;

import java.util.List;

/**
 * 测试类，测试BaseDao
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-14 14:51
 **/

public class TestBaseDao {
    private BaseDao baseDao = new BaseDao();


    @Test
    public void testUpdate() {
        String sql = "insert into test(username, password) values('mojtsh', '123456')";
        int ret = baseDao.update(sql);

        System.out.println(ret);

    }

    @Test
    public void testQueryForOne() {
        String sql = "select * from test where id = ?";
        BeanTest beanTest = baseDao.queryForOne(BeanTest.class, sql, 1);

        System.out.println(beanTest);
    }

    @Test
    public void testQueryForList() {
        String sql = "select * from test";
        List<BeanTest> beanTests = baseDao.queryForList(BeanTest.class, sql);

        System.out.println(beanTests);
    }

    @Test
    public void testQueryForSingle() {
        String sql = "select count(*) from test";
        Object o = baseDao.queryForSingle(sql, Integer.class);

        System.out.println(o);
    }
}
