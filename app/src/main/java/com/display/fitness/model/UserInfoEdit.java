package com.display.fitness.model;

/**
 * @author : 六天
 * @date :   2021/3/26
 * @mail :   wangyijing01@bilibili.com
 */
public class UserInfoEdit {

    private String birthday;
    private String sex;
    private String img;
    private String major;
    private String grade;
    private String name;
    private String password;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    @Override
    public String toString() {
        return "UserInfoEdit{" +
                "birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", img='" + img + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
