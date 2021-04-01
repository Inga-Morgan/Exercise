package com.display.fitness.bean;

/**
 * @author : 六天
 * @date :   2021/3/31
 * @mail :   wangyijing01@bilibili.com
 */
public class PersonIno {
    public String birthday;
    public String createTime;
    public String grade;
    public String id;
    public String name;
    public String password;
    public String profile;
    public String school;
    public String telephone;
    public String userImage;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    @Override
    public String toString() {
        return "PersonIno{" +
                "birthday='" + birthday + '\'' +
                ", createTime='" + createTime + '\'' +
                ", grade='" + grade + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", profile='" + profile + '\'' +
                ", school='" + school + '\'' +
                ", telephone='" + telephone + '\'' +
                ", userImage='" + userImage + '\'' +
                '}';
    }
}