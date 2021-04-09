package com.display.fitness.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : yees
 * @date :   2021/3/26
 * @desc
 */
public class SportInfoBean implements Serializable {

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
    public List<DataSportInfo> data;

    public class DataSportInfo implements Serializable {
        /**
         * sportTime
         */
        /**
         * sportTime : 120.0
         * rankCount : 1
         * dateTime : 2021-02-01 00:00:00
         * strDateTime : 2021-02-01
         */

        @SerializedName("sportTime")
        public Float sportTime;
        /**
         * rankCount
         */
        @SerializedName("rankCount")
        public Integer rankCount;
        /**
         * dateTime
         */
        @SerializedName("dateTime")
        public String dateTime;
        /**
         * strDateTime
         */
        @SerializedName("strDateTime")
        public String strDateTime;
    }

}