package com.cool.todayheadline.utils;

import android.widget.ListView;

import com.cool.todayheadline.bean.Cache_NewsItem;
import com.cool.todayheadline.bean.NewsItem_table;
import com.cool.todayheadline.vo.NewsItem;

import org.litepal.LitePal;

import java.util.List;

/**用法：
 * 首先创建数据库：LitePal.getDatabase();
 * 增加：PreferenceNewsUtil.insertNews(newsItem_table);
 * 删除：PreferenceNewsUtil.deleteNews(user,id);
 * 查询：PreferenceNewsUtil.findAllNews(user);
 *
 */

public class PreferenceNewsUtil {

    public static boolean insertNews(NewsItem_table newsItem_table){
        List<NewsItem_table> list=findAllNews(newsItem_table.getUser());
        for(NewsItem_table news:list){
            if(news.getId_String().equals(newsItem_table.getId_String())){
                return false;
            }
        }
        newsItem_table.save();
        return true;
    }
    public static void deleteNews(String user,String id){
        LitePal.deleteAll(NewsItem_table.class,"id_String=? and user=?",id,user);
    }
    public static List<NewsItem_table> findAllNews(String user){
        List<NewsItem_table> list= LitePal.where("user=?",user).find(NewsItem_table.class);
        return list;
    }
    public static void deleteUserAllNews(String user){
        LitePal.deleteAll(NewsItem_table.class,"user=?",user);
    }

    /*
        对缓存表进行操作
     */

    public static List<Cache_NewsItem> cache_findAllNews(){
        List<Cache_NewsItem> list= LitePal.findAll(Cache_NewsItem.class);
        return list;
    }

    public static Boolean cache_insertNews(Cache_NewsItem cacheNewsItem){
        List<Cache_NewsItem> list=cache_findAllNews();
        for(Cache_NewsItem news:list){
            if(news.getId_String().equals(cacheNewsItem.getId_String())){
                return false;
            }
        }
        cacheNewsItem.save();
        return true;
    }

    public static void cache_deleteAllNews(){
        LitePal.deleteAll(Cache_NewsItem.class);
    }

}
