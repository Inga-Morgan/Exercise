package com.display.fitness.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author : ye's
 * @date :   2021/4/6
 * @desc:
 */
public class TipsBean implements Serializable{
    /**
     * code
     */
    @SerializedName("code")
    public String code;
    /**
     * message
     */
    @SerializedName("message")
    public Object message;
    /**
     * data
     */
    @SerializedName("data")
    public List<TipsInfo> data;



    public class TipsUpInfo implements Serializable {

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
        public TipsInfo data;

    }
}
