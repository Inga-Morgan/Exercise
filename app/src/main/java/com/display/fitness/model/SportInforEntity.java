package com.display.fitness.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author : yees
 * @date :   2021/3/25
 * @desc
 */
public class SportInforEntity {
    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<SprotInfo> data;


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

    public List<SprotInfo> getData() {
        return data;
    }

    public void setData(List<SprotInfo> data) {
        this.data = data;
    }

    public class SprotInfo {
        @SerializedName("sportTime")
        private Integer sportTime;

        @SerializedName("rankCount")
        private Integer rankCount;

        @SerializedName("dateTime")
        private String dateTime;


        public Integer getSportTime() {
            return sportTime;
        }

        public Integer getRankCount() {
            return rankCount;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setSportTime(Integer sportTime) {
            this.sportTime = sportTime;
        }

        public void setRankCount(Integer rankCount) {
            this.rankCount = rankCount;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

    }

    public class SportRequestData {
        private String id;
        private String internal;

        public SportRequestData(String id, String internal) {
            this.id = id;
            this.internal = internal;
        }

        public String getId() {
            return id;
        }

        public String getInternal() {
            return internal;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setInternal(String internal) {
            this.internal = internal;
        }

    }
}
