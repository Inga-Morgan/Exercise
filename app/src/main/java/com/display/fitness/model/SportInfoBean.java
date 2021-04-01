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
 * @author : 六天
 * @date :   2021/3/26
 * @mail :   wangyijing01@bilibili.com
 */
public class SportInfoBean {

    /**
     * code
     */
    /**
     * code : 200
     * message : null
     * data : [{"sportTime":120,"rankCount":1,"dateTime":"2021-02-01 00:00:00","strDateTime":"2021-02-01"},{"sportTime":120,"rankCount":1,"dateTime":"2021-02-02 00:00:00","strDateTime":"2021-02-02"},{"sportTime":120,"rankCount":1,"dateTime":"2021-02-03 00:00:00","strDateTime":"2021-02-03"},{"sportTime":120,"rankCount":1,"dateTime":"2021-02-14 00:00:00","strDateTime":"2021-02-14"},{"sportTime":240,"rankCount":1,"dateTime":"2021-02-15 00:00:00","strDateTime":"2021-02-15"},{"sportTime":120,"rankCount":1,"dateTime":"2021-02-28 00:00:00","strDateTime":"2021-02-28"},{"sportTime":180,"rankCount":1,"dateTime":"2021-03-01 00:00:00","strDateTime":"2021-03-01"},{"sportTime":120,"rankCount":1,"dateTime":"2021-03-02 00:00:00","strDateTime":"2021-03-02"},{"sportTime":60,"rankCount":1,"dateTime":"2021-03-03 00:00:00","strDateTime":"2021-03-03"},{"sportTime":120,"rankCount":1,"dateTime":"2021-03-05 00:00:00","strDateTime":"2021-03-05"},{"sportTime":120,"rankCount":1,"dateTime":"2021-03-15 00:00:00","strDateTime":"2021-03-15"},{"sportTime":120,"rankCount":1,"dateTime":"2021-03-16 00:00:00","strDateTime":"2021-03-16"},{"sportTime":120,"rankCount":1,"dateTime":"2021-03-20 00:00:00","strDateTime":"2021-03-20"},{"sportTime":1.3833333333333333,"rankCount":1,"dateTime":"2021-03-25 00:00:00","strDateTime":"2021-03-25"}]
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

    public static SportInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, SportInfoBean.class);
    }

    public static SportInfoBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SportInfoBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SportInfoBean> arraySportInfoBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SportInfoBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SportInfoBean> arraySportInfoBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SportInfoBean>>() {
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