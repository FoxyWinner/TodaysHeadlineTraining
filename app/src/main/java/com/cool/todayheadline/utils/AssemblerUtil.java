package com.cool.todayheadline.utils;

import com.cool.todayheadline.bean.Data;
import com.cool.todayheadline.bean.Sys;
import com.cool.todayheadline.vo.NewsItem;

public class AssemblerUtil {

    public static NewsItem transform(Sys sys,int i){
        NewsItem newsItem = new NewsItem();
        Data data=sys.getResult().getData()[i];
        newsItem.setId(i+"");
        newsItem.setTitle(data.getTitle());
        newsItem.setAuthor(data.getAuthor_name());
        newsItem.setCategory(data.getCategory());
        newsItem.setDate(data.getDate());
        newsItem.setPic_url(data.getThumbnail_pic_s());
        newsItem.setUrl(data.getUrl());
        return newsItem;
    }
}
