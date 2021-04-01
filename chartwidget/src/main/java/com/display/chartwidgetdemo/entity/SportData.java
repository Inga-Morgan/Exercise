package com.display.chartwidgetdemo.entity;

/**
 * @author : yees
 * @date :   2021/3/11
 * @desc
 */
public class SportData {
    private String sportTime;
    private Integer rankCount;
    private Float dateTime;

    public SportData(String sportTime, Integer rankCount, Float dateTime) {
        this.sportTime = sportTime;
        this.dateTime = dateTime;
        this.rankCount = rankCount;
    }

    public String getSportTime() {
        return sportTime;
    }

    public Integer getRankCount() {
        return rankCount;
    }

    public Float getDateTime() {
        return dateTime;
    }

    public void setSportTime(String sportTime) {
        this.sportTime = sportTime;
    }

    public void setRankCount(Integer rankCount) {
        this.rankCount = rankCount;
    }

    public void setDateTime(Float dateTime) {
        this.dateTime = dateTime;
    }

}
