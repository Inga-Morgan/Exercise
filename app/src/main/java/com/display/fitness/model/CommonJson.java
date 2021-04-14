package com.display.fitness.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author : ye's
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
        /**
         * id
         */
        @SerializedName("id")
        public Integer id;
        /**
         * telephone
         */
        @SerializedName("telephone")
        public String telephone;
        /**
         * password
         */
        @SerializedName("password")
        public Object password;
        /**
         * name
         */
        @SerializedName("name")
        public String name;
        /**
         * birthday
         */
        @SerializedName("birthday")
        public Object birthday;
        /**
         * school
         */
        @SerializedName("school")
        public String school;
        /**
         * grade
         */
        @SerializedName("grade")
        public Integer grade;
        /**
         * userImage
         */
        @SerializedName("userImage")
        public String userImage;
        /**
         * profile
         */
        @SerializedName("profile")
        public Object profile;
        /**
         * createTime
         */
        @SerializedName("createTime")
        public String createTime;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public Integer getGrade() {
            return grade;
        }

        public void setGrade(Integer grade) {
            this.grade = grade;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public Object getProfile() {
            return profile;
        }

        public void setProfile(Object profile) {
            this.profile = profile;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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
