package com.cool.todayheadline.bean;

/**
 * Created by 柳宾辉 on 2018/6/27.
 */

public class Data {
    private String uniquekey;
    private String title;
    private String date;
    private String category;
    private String author_name;
    private String url;
    private String thumbnail_pic_s;
    private String thumbnail_pic_s02;
    private String thumbnail_pic_s03;

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02() {
        return thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    public String getTitle() {
        return title;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public String getUrl() {
        return url;
    }

}
