package com.scu.uars.bean;

import java.math.BigDecimal;

/**
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-17 00:45
 **/

public class GradQuality {
    private Integer univ_id;
    private String univ_name;
    private Integer year;
    private Integer grad_amount;
    private Double prog_rate;
    private Double abroad_rate;
    private Double employ_rate;
    private Double avarage_salary;


    public GradQuality() {
    }

    public GradQuality(Integer univ_id, String univ_name, Integer year, Integer grad_amount, Double prog_rate, Double abroad_rate, Double employ_rate, Double avarage_salary) {
        this.univ_id = univ_id;
        this.univ_name = univ_name;
        this.year = year;
        this.grad_amount = grad_amount;
        this.prog_rate = prog_rate;
        this.abroad_rate = abroad_rate;
        this.employ_rate = employ_rate;
        this.avarage_salary = avarage_salary;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getGrad_amount() {
        return grad_amount;
    }

    public void setGrad_amount(Integer grad_amount) {
        this.grad_amount = grad_amount;
    }

    public Double getProg_rate() {
        return prog_rate;
    }

    public void setProg_rate(Double prog_rate) {
        this.prog_rate = prog_rate;
    }

    public Double getAbroad_rate() {
        return abroad_rate;
    }

    public void setAbroad_rate(Double abroad_rate) {
        this.abroad_rate = abroad_rate;
    }

    public Double getEmploy_rate() {
        return employ_rate;
    }

    public void setEmploy_rate(Double employ_rate) {
        this.employ_rate = employ_rate;
    }

    public Double getAvarage_salary() {
        return avarage_salary;
    }

    public void setAvarage_salary(Double avarage_salary) {
        this.avarage_salary = avarage_salary;
    }

    @Override
    public String toString() {
        return "GraduQuality{" +
                "univ_id=" + univ_id +
                ", univ_name='" + univ_name + '\'' +
                ", year=" + year +
                ", grad_amount=" + grad_amount +
                ", prog_rate=" + prog_rate +
                ", abroad_rate=" + abroad_rate +
                ", employ_rate=" + employ_rate +
                ", avarage_salary=" + avarage_salary +
                '}';
    }

    public void fix() {
        this.abroad_rate = new BigDecimal(this.abroad_rate * 100).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
        this.prog_rate = new BigDecimal(this.prog_rate * 100).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
        this.employ_rate = new BigDecimal(this.employ_rate * 100).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
