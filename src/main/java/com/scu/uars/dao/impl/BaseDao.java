package com.scu.uars.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @Program: UARS
 * @Description: 数据库访问Dao层基础类
 * @Author: MOATSH
 * @Create: 2023-07-14 14:07
 **/

public class BaseDao {
    private JdbcTemplate jdbcTemplate;

    public BaseDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("UARS.xml");
        this.jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    }

    /**
     * 执行非查询sql，返回值为影响行数
     * 执行失败返回-1
     * @param sql sql语句
     * @param args sql参数
     * @return int
     */
    public int update(String sql, @Nullable Object... args) {
        int result = -1;
        try {
            result = jdbcTemplate.update(sql, args);
        }
        catch (Exception e){
            System.out.println("BaseDao update error!");
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 执行查询返回单个对象
     * 执行失败或查找为空返回null
     * @param type class对象
     * @param sql sql语句
     * @param args sql参数
     * @return T
     */
    public <T> T queryForOne(Class<T> type, String sql, @Nullable Object... args) {
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(type), args);
        }
        catch (Exception e){
            System.out.println("BaseDao query error!");
        }

        return null;
    }

    /**
     * 执行查询返回多个对象
     * 执行失败返回null
     * @param type class
     * @param sql sql语句
     * @param args sql参数
     * @return List<T>
     */
    public <T> List<T> queryForList(Class<T> type, String sql, @Nullable Object... args) {
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(type), args);
        }
        catch (Exception e){
            System.out.println("BaseDao queryForList error!");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 执行查询返回单个数值
     * 执行失败返回null
     * @param sql sql语句
     * @param type 需要返回的类型
     * @param args sql参数
     * @return T
     */
    public <T> Object queryForSingle(String sql, Class<T> type, @Nullable Object... args) {
        try {
            return jdbcTemplate.queryForObject(sql, type, args);
        }
        catch (Exception e) {
            System.out.println("BaseDao queryForSingle error!");
            e.printStackTrace();
        }

        return null;
    }
}
