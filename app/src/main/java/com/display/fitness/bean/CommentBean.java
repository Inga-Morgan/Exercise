package com.display.fitness.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author moos
 * @date 2018/4/20
 */

public class CommentBean implements Serializable {
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
    public DataSportInfo data;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setData(DataSportInfo data) {
        this.data = data;
    }

    public DataSportInfo getData() {
        return data;
    }

    public class DataSportInfo implements Serializable {

        /**
         * total
         */
        @SerializedName("total")
        public Integer total;
        /**
         * list
         */
        @SerializedName("list")
        public List<CommentDetailBean> list;

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal() {
            return total;
        }

        public void setList(List<CommentDetailBean> list) {
            this.list = list;
        }

        public List<CommentDetailBean> getList() {
            return list;
        }

    }

    public class ReplyCommentBean implements Serializable {

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
        public CommentDetailBean data;

    }
}
