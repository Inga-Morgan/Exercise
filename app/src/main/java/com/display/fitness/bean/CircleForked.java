package com.display.fitness.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author : 六天
 * @date : 2021/4/14
 * @desc :
 */
public class CircleForked implements Serializable{

    /**
     * code
     */
    @SerializedName("code")
    public String code;
    /**
     * message
     */
    @SerializedName("message")
    public String message;
    /**
     * data
     */
    @SerializedName("data")
    public List<CircleIsFork> data;

    public  class CircleIsFork implements Serializable {
        /**
         * id
         */
        @SerializedName("id")
        public String id;
        /**
         * name
         */
        @SerializedName("name")
        public String name;
        /**
         * notice
         */
        @SerializedName("notice")
        public String notice;
        /**
         * user
         */
        @SerializedName("user")
        public String user;
        /**
         * icon
         */
        @SerializedName("icon")
        public String icon;
        /**
         * createTime
         */
        @SerializedName("createTime")
        public String createTime;
        /**
         * memberCount
         */
        @SerializedName("memberCount")
        public String memberCount;
        /**
         * postCount
         */
        @SerializedName("postCount")
        public String postCount;

        @Override
        public String toString() {
            return "CircleIsFork{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", notice='" + notice + '\'' +
                    ", user='" + user + '\'' +
                    ", icon='" + icon + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", memberCount='" + memberCount + '\'' +
                    ", postCount='" + postCount + '\'' +
                    '}';
        }
    }
}
