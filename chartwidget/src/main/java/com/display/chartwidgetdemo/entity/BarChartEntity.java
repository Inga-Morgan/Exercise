package com.display.chartwidgetdemo.entity;

/**
 * @author : 六天
 * @date :   2021/3/11
 * @mail :   wangyijing01@bilibili.com
 */
public class BarChartEntity {

    private String xLabel;
    private Float yValue;

    public BarChartEntity(String xLabel, Float yValue) {
        this.xLabel = xLabel;
        this.yValue = yValue;

    }

    public String getxLabel() {
        return xLabel;
    }

    public void setxLabel(String xLabel) {
        this.xLabel = xLabel;
    }

    public BarChartEntity(Float yValue) {
        this.yValue = yValue;
    }

    public Float getyValue() {
        return yValue;
    }

    public void setyValue(Float yValue) {
        this.yValue = yValue;

    }
}


