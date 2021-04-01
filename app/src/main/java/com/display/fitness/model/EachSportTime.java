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

    public static EachSportTime objectFromData(String str) {

        return new Gson().fromJson(str, EachSportTime.class);
    }

    public static EachSportTime objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), EachSportTime.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<EachSportTime> arrayEachSportTimeFromData(String str) {

        Type listType = new TypeToken<ArrayList<EachSportTime>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<EachSportTime> arrayEachSportTimeFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<EachSportTime>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

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

        public DataSportInfo objectFromData(String str) {

            return new Gson().fromJson(str, DataSportInfo.class);
        }

        public DataSportInfo objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataSportInfo.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public List<DataSportInfo> arrayDataSportInfoFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataSportInfo>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public List<DataSportInfo> arrayDataSportInfoFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<DataSportInfo>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }
    }
}
