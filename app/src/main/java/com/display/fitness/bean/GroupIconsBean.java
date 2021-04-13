package com.display.fitness.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author : ye's
 * @date :   2021/4/8
 * @desc :
 */
public class GroupIconsBean implements Serializable {

    /**
     * code
     */
    @SerializedName("code")
    public String code;
    /**
     * message
     */
    @SerializedName("message")
    public Object message;
    /**
     * data
     */
    @SerializedName("data")
    public List<CircleIcons> data;

    public class CircleIcons implements Serializable  {
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

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(String memberCount) {
            this.memberCount = memberCount;
        }

        public String getPostCount() {
            return postCount;
        }

        public void setPostCount(String postCount) {
            this.postCount = postCount;
        }
    }

}
