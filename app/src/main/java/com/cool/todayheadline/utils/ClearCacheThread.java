package com.cool.todayheadline.utils;

import com.cool.todayheadline.bean.Cache_NewsItem;
import com.cool.todayheadline.bean.Data;
import com.cool.todayheadline.bean.Sys;

import java.util.List;

public class ClearCacheThread {
    public static void clearCache(final Sys sys){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int total=sys.getResult().getData().length;
                List<Cache_NewsItem> cacheNewsItemList=PreferenceNewsUtil.cache_findAllNews();
                for(int i=0;i<total;i++){
                    int flag=0;
                    Data data=sys.getResult().getData()[i];
                    for(Cache_NewsItem cacheNewsItem:cacheNewsItemList){
                        if(cacheNewsItem.getId_String().equals(data.getUniquekey())){
                            if(flag>=1){
                                PreferenceNewsUtil.cache_deleteNewsById(cacheNewsItem.getId());
                            }
                            flag++;
                        }
                    }
                }

            }
        }).start();
    }
}
