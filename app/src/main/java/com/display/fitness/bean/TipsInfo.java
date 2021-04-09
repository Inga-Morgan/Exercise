package com.display.fitness.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author : 六天
 * @date :   2021/4/9
 * @mail :   wangyijing01@bilibili.com
 */
public class TipsInfo implements Serializable {
    /**
     * id
     */
    @SerializedName("id")
    public String id;
    /**
     * groupId
     */
    @SerializedName("groupId")
    public String groupId;
    /**
     * user
     */
    @SerializedName("user")
    public String user;
    /**
     * userName
     */
    @SerializedName("userName")
    public String userName;
    /**
     * userImage
     */
    @SerializedName("userImage")
    public String userImage;
    /**
     * content
     */
    @SerializedName("content")
    public String content;
    /**
     * images
     */
    @SerializedName("images")
    public String images;
    /**
     * views
     */
    @SerializedName("views")
    public String views;
    /**
     * createTime
     */
    @SerializedName("createTime")
    public String createTime;
    /**
     * updateTime
     */
    @SerializedName("updateTime")
    public String updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TipsInfo{" +
                "id='" + id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", user='" + user + '\'' +
                ", userName='" + userName + '\'' +
                ", userImage='" + userImage + '\'' +
                ", content='" + content + '\'' +
                ", images='" + images + '\'' +
                ", views='" + views + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}