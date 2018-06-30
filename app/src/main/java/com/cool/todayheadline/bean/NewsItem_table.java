package com.cool.todayheadline.bean;

import org.litepal.crud.LitePalSupport;

public class NewsItem_table extends LitePalSupport
{
    private int id;
    private String id_String;
    private String category;
    private String pic_url;
    private String title;
    private String author;
    private String date;
    private String url;
    private String user;
    public NewsItem_table(){}

    public NewsItem_table(String id_String, String category, String pic_url, String title, String author, String date, String url, String user) {
        this.id_String = id_String;
        this.category = category;
        this.pic_url = pic_url;
        this.title = title;
        this.author = author;
        this.date = date;
        this.url = url;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId_String() {
        return id_String;
    }

    public void setId_String(String id_String) {
        this.id_String = id_String;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getPic_url()
    {
        return pic_url;
    }

    public void setPic_url(String pic_url)
    {
        this.pic_url = pic_url;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
