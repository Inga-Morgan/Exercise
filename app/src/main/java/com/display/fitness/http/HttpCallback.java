package com.display.fitness.http;

/**
 * @author : ye's
 * @date :   2021/3/22
 * @desc :
 */
public abstract class HttpCallback<T> {
    public abstract void onSuccess(T t);

    public abstract void onFail(Exception e);

    public void onFinish() {
    }
}
