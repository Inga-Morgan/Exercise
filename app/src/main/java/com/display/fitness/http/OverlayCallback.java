package com.display.fitness.http;

import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * @author : 六天
 * @date :   2021/3/26
 * @mail :   wangyijing01@bilibili.com
 */
public abstract class OverlayCallback<T> extends Callback<T> {

    private Class<T> clazz;
    private Gson gson;

    public OverlayCallback(Class<T> clazz) {
        this.clazz = clazz;
        gson = new Gson();
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        try {
            String string = response.body().string();
            Log.e("TAG_debug_jsonString",string);
//            SportInforEntity sportInforEntity = new Gson().fromJson(string,SportInforEntity.class);
//            List<SportInforEntity.SprotInfo> sprotInfos = sportInforEntity.getData();

//            Log.e("TAG_gson",gson.fromJson(string,clazz).toString());
//            for (int i = 0; i < sprotInfos.size();i++){
//                Log.e("TAG_LIST",sprotInfos.get(i).toString());
//            }
            return gson.fromJson(string,clazz);
        }catch (Exception e){
        }

        return null;
    }
}