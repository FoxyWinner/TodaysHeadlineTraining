package com.cool.todayheadline.utils;

import com.cool.todayheadline.bean.NewsItem_table;
import com.cool.todayheadline.vo.NewsItem;

import org.litepal.LitePal;

import java.util.List;

/**用法：
 * 首先创建数据库：LitePal.getDatabase();
 * 增加：PreferenceNewsUtil.insertNews(newsItem_table);
 * 删除：PreferenceNewsUtil.deleteNews(user,id);
 * 查询：PreferenceNewsUtil.findAllNews();
 *
 */

public class PreferenceNewsUtil {

    public static boolean insertNews(NewsItem_table newsItem_table){
        List<NewsItem_table> list=findAllNews();
        for(NewsItem_table news:list){
            if(news.getId_String().equals(newsItem_table.getId_String())
                    && news.getUser().equals(newsItem_table.getUser())){
                return false;
            }
        }
        newsItem_table.save();
        return true;
    }
    public static void deleteNews(String user,String id){
        LitePal.deleteAll(NewsItem_table.class,"id_String=? and user=?",id,user);
    }
    public static List<NewsItem_table> findAllNews(){
        List<NewsItem_table> list= LitePal.findAll(NewsItem_table.class);
        return list;
    }

}
