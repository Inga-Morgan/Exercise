package com.display.fitness.bean

import com.blankj.utilcode.util.LogUtils
import com.display.fitness.model.CommonJson

/**
 * @author : 六天
 * @date : 2021/4/14
 * @desc :
 */
object UsersInfo {

    private var userInfo: CommonJson.UserInfo? = null
    var forkedList: List<CircleForked.CircleIsFork>? = null

    fun getUserInfo(): CommonJson.UserInfo? {
        return userInfo
    }

    fun setUserInfo(userInfo: CommonJson.UserInfo) {
        this.userInfo = userInfo
    }

    fun getCircleForked(): List<CircleForked.CircleIsFork>? {
        return forkedList
    }

    fun setCircleForked(forkedList: List<CircleForked.CircleIsFork>) {
        this.forkedList = forkedList
    }

    fun isForked(id: String?): Boolean {
        if (forkedList != null) {
            for (item in forkedList!!) {
                if (id === item.id) {
                    return true
                }
            }
            LogUtils.e("forkedList not null")
        }
        LogUtils.e("forkedList is null")
        return false
    }
}