package com.scu.uars.bean;

/**
 * @Program: UARS
 * @Description:
 * @Author: MOATSH
 * @Create: 2023-07-18 22:19
 **/

public class MinAndMaxScore {
    private Integer minScore;
    private Integer maxScore;


    public MinAndMaxScore() {
    }

    public MinAndMaxScore(Integer minScore, Integer maxScore) {
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return "MinAndMaxScore{" +
                "minScore=" + minScore +
                ", maxScore=" + maxScore +
                '}';
    }
}
