package com.display.fitness.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author : 六天
 * @date : 2021/4/14
 * @desc :
 */
public class CircleInfoBean implements Serializable {

    /**
     * code
     */
    @SerializedName("code")
    public String code;
    /**
     * message
     */
    @SerializedName("message")
    public String message;
    /**
     * data
     */
    @SerializedName("data")
    public CircleForked.CircleIsFork data;

}
