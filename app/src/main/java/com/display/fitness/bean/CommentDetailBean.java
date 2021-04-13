package com.display.fitness.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author : ye's
 * @date :   2021/4/9
 * @desc :
 */
public class CommentDetailBean implements Serializable {
    /**
     * id
     */
    @SerializedName("id")
    public String id;
    /**
     * post
     */
    @SerializedName("post")
    public String post;
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
     * replyTotal
     */
    @SerializedName("replyTotal")
    public Integer replyTotal;
    /**
     * replayList
     */
    @SerializedName("replayList")
    public List<?> replayList;
    /**
     * comment
     */
    @SerializedName("comment")
    public String comment;
    /**
     * agreeCount
     */
    @SerializedName("agreeCount")
    public Integer agreeCount;
    /**
     * image
     */
    @SerializedName("image")
    public String image;
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

    private List<ReplyDetailBean> replyList;

    public CommentDetailBean(String userName, String comment, String createTime) {
        this.userName = userName;
        this.comment = comment;
        this.createTime = createTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setReplyTotal(int replyTotal) {
        this.replyTotal = replyTotal;
    }

    public int getReplyTotal() {
        return replyTotal;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setReplyList(List<ReplyDetailBean> replyList) {
        this.replyList = replyList;
    }

    public List<ReplyDetailBean> getReplyList() {
        return replyList;
    }
}