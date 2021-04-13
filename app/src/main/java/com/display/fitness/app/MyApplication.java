package com.display.fitness.app;

import android.app.Application;
import android.content.Context;

/**
 * @author : ye's
 * @date :   2021/3/25
 * @desc :
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