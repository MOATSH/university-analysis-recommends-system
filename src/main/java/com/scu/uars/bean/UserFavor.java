package com.scu.uars.bean;

/**
 * user_favor实体类
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-15 23:05
 **/

public class UserFavor {
    private Integer user_id;
    private Integer favor_univ_id;


    public UserFavor() {
    }

    public UserFavor(Integer user_id, Integer favor_univ_id) {
        this.user_id = user_id;
        this.favor_univ_id = favor_univ_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFavor_univ_id() {
        return favor_univ_id;
    }

    public void setFavor_univ_id(Integer favor_univ_id) {
        this.favor_univ_id = favor_univ_id;
    }

    @Override
    public String toString() {
        return "UserFavor{" +
                "user_id=" + user_id +
                ", favor_univ_id=" + favor_univ_id +
                '}';
    }
}
