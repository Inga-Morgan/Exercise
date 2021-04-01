package com.display.fitness.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author : yees
 * @date :   2021/3/18
 * @desc
 */
public class CommonJson {

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private UserInfo userInfo;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public class UserInfo {

        @SerializedName("id")
        private String id;

        @SerializedName("telephone")
        private String telephone;


        @SerializedName("password")
        private String password;

        @SerializedName("name")
        private String name;


        @SerializedName("birthday")
        private String birthday;


        @SerializedName("major")
        private String major;


        @SerializedName("grade")
        private String grade;

        @SerializedName("userImage")
        private String userImage;


        @SerializedName("profile")
        private String profile;


        @SerializedName("createTime")
        private String createTime;

        public String getId() {
            return id;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getMajor() {
            return major;
        }

        public String getGrade() {
            return grade;
        }

        public String getUserImage() {
            return userImage;
        }

        public String getProfile() {
            return profile;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "id='" + id + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", password='" + password + '\'' +
                    ", name='" + name + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", major='" + major + '\'' +
                    ", grade='" + grade + '\'' +
                    ", userImage='" + userImage + '\'' +
                    ", profile='" + profile + '\'' +
                    ", createTime='" + createTime + '\'' +
                    '}';
        }
    }

    public class LoginParam {
        private String telephone;
        private String password;


        public LoginParam(String telephone, String password) {
            this.telephone = telephone;
            this.password = password;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "LoginParam{" +
                    "telephone='" + telephone + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

}
