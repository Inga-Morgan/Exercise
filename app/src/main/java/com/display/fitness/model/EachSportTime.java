package com.display.fitness.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : yees
 * @date :   2021/3/29
 * @desc
 */
public class EachSportTime {

    /**
     * code
     */
    /**
     * code : 200
     * message : null
     * data : [{"sportTime":601.3833,"sport":"游泳"},{"sportTime":540,"sport":"跑步"},{"sportTime":540,"sport":"瑜伽"}]
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

    public class DataSportInfo {
        /**
         * sportTime
         */
        /**
         * sportTime : 601.3833
         * sport : 游泳
         */

        @SerializedName("sportTime")
        public Float sportTime;
        /**
         * sport
         */
        @SerializedName("sport")
        public String sport;
    }
}
