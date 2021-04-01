package com.display.fitness.http;


import com.display.fitness.model.CommonJson;

/**
 * @author : yees
 * @date :   2021/3/22
 * @desc
 */
public class ClientHelper {
    private static CommonJson.UserInfo userInfo;

    public static void setUserData(CommonJson commonJson) {
        userInfo = commonJson.getUserInfo();
    }
}
