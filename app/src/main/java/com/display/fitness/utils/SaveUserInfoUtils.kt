package com.display.fitness.utils

import com.display.fitness.model.CommonJson

/**
 * @author : 六天
 * @date :   2021/3/25
 * @mail :   wangyijing01@bilibili.com
 */
object SaveUserInfoUtils {

    private var userInfo: CommonJson.UserInfo ?= null
    fun getUserInfo(): CommonJson.UserInfo? {
        return userInfo
    }

    fun setUserInfo(userInfo: CommonJson.UserInfo) {
        this.userInfo = userInfo
    }

}