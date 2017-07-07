package com.example.snail.scrolltextview;

/**
 * Created by Snail on 2017/7/7.
 */

public class NoticeBean {
    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    private String title;
    private String date;

    public NoticeBean(String title, String date) {
        this.title = title;
        this.date = date;
    }
}
