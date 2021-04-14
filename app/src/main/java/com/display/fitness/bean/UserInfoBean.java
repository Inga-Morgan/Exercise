package com.display.fitness.bean;

import java.io.File;

/**
 * @author : 六天
 * @date :   2021/3/31
 * @desc:
 */
public class UserInfoBean {
    public String userName;
    public File userImg;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public File getUserImg() {
        return userImg;
    }

    public void setUserImg(File userImg) {
        this.userImg = userImg;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "userName='" + userName + '\'' +
                ", userImg=" + userImg +
                '}';
    }
}
