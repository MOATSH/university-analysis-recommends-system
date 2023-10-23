package com.scu.uars.bean;

/**
 * 测试javaBean类
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-14 15:09
 **/

public class BeanTest {
    private Integer id;
    private String userName;
    private String password;


    public BeanTest() {
    }

    public BeanTest(Integer id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "test{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
