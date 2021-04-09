package com.display.fitness.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : 六天
 * @date :   2021/4/9
 * @mail :   wangyijing01@bilibili.com
 */
public class TipsCommentInfo implements Serializable {
    /**
     * id
     */
    @SerializedName("id")
    public Integer id;
    /**
     * post
     */
    @SerializedName("post")
    public Integer post;
    /**
     * user
     */
    @SerializedName("user")
    public Integer user;
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
    public Object image;
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


}
