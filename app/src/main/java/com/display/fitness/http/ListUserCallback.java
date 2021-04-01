package com.display.fitness.http;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;


/**
 * Json封装
 * Created by wcy on 2015/12/20.
 */

public abstract class ListUserCallback<T> extends Callback<List<T>> {

    @Override
    public List<T> parseNetworkResponse(Response response, int id) throws IOException {
        String string = response.body().string();
        List<T> list = new Gson().fromJson(string, List.class);
        return list;
    }
}