package com.scu.uars.bean;

/**
 * univ_info对应的JavaBean
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-15 22:47
 **/

public class UnivInfo {
    private Integer univ_id;
    private String univ_name;
    private String univ_rank;
    private String prov_name;
    private String univ_info;
    private Double sex_ratio;
    private Integer MDP_amount;
    private Integer key_disp_amount;
    private Integer key_lab_amount;


    public UnivInfo() {
    }

    public UnivInfo(Integer univ_id, String univ_name, String univ_rank, String prov_name, String univ_info, Double sex_ratio, Integer MDP_amount, Integer key_disp_amount, Integer key_lab_amount) {
        this.univ_id = univ_id;
        this.univ_name = univ_name;
        this.univ_rank = univ_rank;
        this.prov_name = prov_name;
        this.univ_info = univ_info;
        this.sex_ratio = sex_ratio;
        this.MDP_amount = MDP_amount;
        this.key_disp_amount = key_disp_amount;
        this.key_lab_amount = key_lab_amount;
    }

    public Integer getUniv_id() {
        return univ_id;
    }

    public void setUniv_id(Integer univ_id) {
        this.univ_id = univ_id;
    }

    public String getUniv_name() {
        return univ_name;
    }

    public void setUniv_name(String univ_name) {
        this.univ_name = univ_name;
    }

    public String getUniv_rank() {
        return univ_rank;
    }

    public void setUniv_rank(String univ_rank) {
        this.univ_rank = univ_rank;
    }

    public String getProv_name() {
        return prov_name;
    }

    public void setProv_name(String prov_name) {
        this.prov_name = prov_name;
    }

    public String getUniv_info() {
        return univ_info;
    }

    public void setUniv_info(String univ_info) {
        this.univ_info = univ_info;
    }

    public Double getSex_ratio() {
        return sex_ratio;
    }

    public void setSex_ratio(Double sex_ratio) {
        this.sex_ratio = sex_ratio;
    }

    public Integer getMDP_amount() {
        return MDP_amount;
    }

    public void setMDP_amount(Integer MDP_amount) {
        this.MDP_amount = MDP_amount;
    }

    public Integer getKey_disp_amount() {
        return key_disp_amount;
    }

    public void setKey_disp_amount(Integer key_disp_amount) {
        this.key_disp_amount = key_disp_amount;
    }

    public Integer getKey_lab_amount() {
        return key_lab_amount;
    }

    public void setKey_lab_amount(Integer key_lab_amount) {
        this.key_lab_amount = key_lab_amount;
    }

    @Override
    public String toString() {
        return "UnivInfo{" +
                "univ_id=" + univ_id +
                ", univ_name='" + univ_name + '\'' +
                ", univ_rank='" + univ_rank + '\'' +
                ", prov_name='" + prov_name + '\'' +
                ", univ_info='" + univ_info + '\'' +
                ", sex_ratio=" + sex_ratio +
                ", MDP_amount=" + MDP_amount +
                ", key_disp_amount=" + key_disp_amount +
                ", key_lab_amount=" + key_lab_amount +
                '}';
    }
}
