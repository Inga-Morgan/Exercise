package com.display.fitness.login;

/**
 * @author : 六天
 * @date :   2021/3/10
 * @mail :   wangyijing01@bilibili.com
 */
public interface DisposeDataListener {

    /**
     * 请求成功回调事件处理
     */
    public void onSuccess(Object responseObj);

    /**
     * 请求失败回调事件处理
     */
    public void onFailure(Object reasonObj);

}
