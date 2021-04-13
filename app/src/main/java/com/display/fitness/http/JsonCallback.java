package com.display.fitness.http;

import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * @author : ye's
 * @date :   2021/3/22
 * @desc : Json封装
 */
public abstract class JsonCallback<T> extends Callback<T> {
    private Class<T> clazz;
    private Gson gson;

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
        gson = new Gson();
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws IOException {
        try {
            String jsonString = response.body().string();
            Log.e("TAG_debug_jsonString", jsonString);
            Log.e("TAG_gson", gson.fromJson(jsonString, clazz).toString());
            return gson.fromJson(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
