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
public class DataDTO {
    /**
     * sportTime : 120
     * rankCount : 1
     * dateTime : 2021-02-01 00:00:00
     */

    @SerializedName("sportTime")
    private Integer sportTime;
    @SerializedName("rankCount")
    private Integer rankCount;
    @SerializedName("dateTime")
    private String dateTime;

    public static DataDTO objectFromData(String str) {

        return new Gson().fromJson(str, DataDTO.class);
    }

    public static DataDTO objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), DataDTO.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<DataDTO> arrayDataDTOFromData(String str) {

        Type listType = new TypeToken<ArrayList<DataDTO>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<DataDTO> arrayDataDTOFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<DataDTO>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }
}
