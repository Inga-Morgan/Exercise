package com.display.fitness.app;

import android.app.Application;
import android.content.Context;

/**
 * @author : 六天
 * @date :   2021/3/25
 * @mail :   wangyijing01@bilibili.com
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}