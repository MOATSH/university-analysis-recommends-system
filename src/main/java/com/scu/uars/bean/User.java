package com.scu.uars.bean;

/**
 * User javaBean类
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-15 08:55
 **/

public class User {
    private Integer id;
    private String username;
    private String password;
    private String province;


    public User() {
    }

    public User(Integer id, String username, String password, String province) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.province = province;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
