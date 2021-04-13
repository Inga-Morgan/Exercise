package com.display.fitness.bean;


import java.io.Serializable;

/**
 * @author : ye's
 * @date :   2021/3/15
 * @desc :
 */
public class HomeMenuItem implements Serializable {

    private int icon;
    private String text;
    private String url;

    public HomeMenuItem(String text, String url) {
        this.text = text;
        this.url = url;
    }

    public HomeMenuItem(int icon, String text, String url) {
        this.icon = icon;
        this.text = text;
        this.url = url;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
