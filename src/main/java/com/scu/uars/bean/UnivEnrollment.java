package com.scu.uars.bean;

/**
 * univ_enrollment实体JavaBean
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-15 22:56
 **/

public class UnivEnrollment {
    private Integer univ_id;
    private String prov_name;
    private Integer year;
    private String major_name;
    private Integer enroll_amount;
    private Integer lowest_rank;
    private String arts_science;
    private Integer lowest_score;


    public UnivEnrollment() {
    }

    public UnivEnrollment(Integer univ_id, String prov_name, Integer year, String major_name, Integer enroll_amount, Integer lowest_rank, String arts_science, Integer lowest_score) {
        this.univ_id = univ_id;
        this.prov_name = prov_name;
        this.year = year;
        this.major_name = major_name;
        this.enroll_amount = enroll_amount;
        this.lowest_rank = lowest_rank;
        this.arts_science = arts_science;
        this.lowest_score = lowest_score;
    }

    public Integer getUniv_id() {
        return univ_id;
    }

    public void setUniv_id(Integer univ_id) {
        this.univ_id = univ_id;
    }

    public String getProv_name() {
        return prov_name;
    }

    public void setProv_name(String prov_name) {
        this.prov_name = prov_name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public Integer getEnroll_amount() {
        return enroll_amount;
    }

    public void setEnroll_amount(Integer enroll_amount) {
        this.enroll_amount = enroll_amount;
    }

    public Integer getLowest_rank() {
        return lowest_rank;
    }

    public void setLowest_rank(Integer lowest_rank) {
        this.lowest_rank = lowest_rank;
    }

    public String getArts_science() {
        return arts_science;
    }

    public void setArts_science(String arts_science) {
        this.arts_science = arts_science;
    }

    public Integer getLowest_score() {
        return lowest_score;
    }

    public void setLowest_score(Integer lowest_score) {
        this.lowest_score = lowest_score;
    }

    @Override
    public String toString() {
        return "UnivEnrollment{" +
                "univ_id=" + univ_id +
                ", prov_name='" + prov_name + '\'' +
                ", year=" + year +
                ", major_name='" + major_name + '\'' +
                ", enroll_amount=" + enroll_amount +
                ", lowest_rank=" + lowest_rank +
                ", arts_science='" + arts_science + '\'' +
                ", lowest_score=" + lowest_score +
                '}';
    }
}
