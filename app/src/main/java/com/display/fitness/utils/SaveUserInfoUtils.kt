package com.display.fitness.utils

import com.display.fitness.model.CommonJson

/**
 * @author : ye's
 * @date :   2021/3/25
 *@desc
 */
object SaveUserInfoUtils {

    private var userInfo: CommonJson.UserInfo? = null
    fun getUserInfo(): CommonJson.UserInfo? {
        return userInfo
    }

    fun setUserInfo(userInfo: CommonJson.UserInfo) {
        this.userInfo = userInfo
    }

}