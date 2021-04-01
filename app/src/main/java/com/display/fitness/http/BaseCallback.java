package com.display.fitness.http;

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;

/**
 * @author : 六天
 * @date :   2021/3/26
 * @mail :   wangyijing01@bilibili.com
 */
public abstract class BaseCallback extends Callback {
    @Override
    public void onError(Call call, Exception e, int id) {
        // TODO:
    }
}