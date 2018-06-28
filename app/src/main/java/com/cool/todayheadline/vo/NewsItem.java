package com.cool.todayheadline.vo;

public class NewsItem
{
    private String id;
    private String category;
    private String pic_url;
    private String title;
    private String author;
    private String date;

    public NewsItem(){}
    public NewsItem(String id, String category, String pic_url, String title, String author, String date)
    {
        this.id = id;
        this.category = category;
        this.pic_url = pic_url;
        this.title = title;
        this.author = author;
        this.date = date;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
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
}
