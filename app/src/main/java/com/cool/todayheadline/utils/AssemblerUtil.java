package com.cool.todayheadline.utils;

import com.cool.todayheadline.bean.Cache_NewsItem;
import com.cool.todayheadline.bean.Data;
import com.cool.todayheadline.bean.NewsItem_table;
import com.cool.todayheadline.bean.Sys;
import com.cool.todayheadline.vo.NewsItem;

import java.util.ArrayList;
import java.util.List;

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

    public static List<NewsItem> tableToNews(List<NewsItem_table> list){
        List<NewsItem> newsItemList=new ArrayList<NewsItem>();
        for(NewsItem_table news:list){
            NewsItem newsItem=new NewsItem();
            newsItem.setUrl(news.getUrl());
            newsItem.setId(news.getId_String());
            newsItem.setPic_url(news.getPic_url());
            newsItem.setDate(news.getDate());
            newsItem.setCategory(news.getCategory());
            newsItem.setAuthor(news.getAuthor());
            newsItem.setTitle(news.getTitle());
            newsItemList.add(newsItem);
        }
        return newsItemList;
    }
    public static List<Cache_NewsItem> NewsItemToCacheTable(List<NewsItem> list){
        List<Cache_NewsItem> cacheNewsItems=new ArrayList<Cache_NewsItem>();
        for(NewsItem news:list){
            Cache_NewsItem cacheNewsItem=new Cache_NewsItem();
            cacheNewsItem.setUrl(news.getUrl());
            cacheNewsItem.setId_String(news.getId());
            cacheNewsItem.setPic_url(news.getPic_url());
            cacheNewsItem.setDate(news.getDate());
            cacheNewsItem.setCategory(news.getCategory());
            cacheNewsItem.setAuthor(news.getAuthor());
            cacheNewsItem.setTitle(news.getTitle());
            cacheNewsItems.add(cacheNewsItem);
        }
        return cacheNewsItems;
    }

    public static List<NewsItem> CacheTableTONewsItem(List<Cache_NewsItem> list){
        List<NewsItem> newsItemList=new ArrayList<NewsItem>();
        for(Cache_NewsItem news:list){
            NewsItem newsItem=new NewsItem();
            newsItem.setUrl(news.getUrl());
            newsItem.setId(news.getId_String());
            newsItem.setPic_url(news.getPic_url());
            newsItem.setDate(news.getDate());
            newsItem.setCategory(news.getCategory());
            newsItem.setAuthor(news.getAuthor());
            newsItem.setTitle(news.getTitle());
            newsItemList.add(newsItem);
        }
        return newsItemList;
    }
}
