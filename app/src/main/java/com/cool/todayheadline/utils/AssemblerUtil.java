package com.cool.todayheadline.utils;

import com.cool.todayheadline.bean.Data;
import com.cool.todayheadline.bean.NewsItem_table;
import com.cool.todayheadline.bean.Sys;
import com.cool.todayheadline.vo.NewsItem;

public class AssemblerUtil {

    public static NewsItem transform(Sys sys,int i){
        NewsItem newsItem = new NewsItem();
        Data data=sys.getResult().getData()[i];
        newsItem.setId(data.getUniquekey());
        newsItem.setTitle(data.getTitle());
        newsItem.setAuthor(data.getAuthor_name());
        newsItem.setCategory(data.getCategory());
        newsItem.setDate(data.getDate());
        newsItem.setPic_url(data.getThumbnail_pic_s());
        newsItem.setUrl(data.getUrl());
        return newsItem;
    }

    public static NewsItem_table transformToPOJO(NewsItem newsItem)
    {
        NewsItem_table newsItem_table = new NewsItem_table(newsItem.getId(),
                newsItem.getCategory(),
                newsItem.getPic_url(),
                newsItem.getTitle(),
                newsItem.getAuthor(),
                newsItem.getDate(),
                newsItem.getUrl(),
                Const.USER_ID+"");
        return newsItem_table;
    }
}
