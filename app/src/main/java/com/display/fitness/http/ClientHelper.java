package com.display.fitness.http;


import com.display.fitness.model.CommonJson;

/**
 * @author : 六天
 * @date :   2021/3/22
 * @mail :   wangyijing01@bilibili.com
 */
public class ClientHelper {
    private static CommonJson.UserInfo userInfo;
    public static void setUserData(CommonJson commonJson) {
        userInfo = commonJson.getUserInfo();
    }
}
