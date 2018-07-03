package com.cool.todayheadline.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cool.todayheadline.Services.NewsNotificationService;
import com.cool.todayheadline.bean.Cache_NewsItem;
import com.cool.todayheadline.bean.Sys;
import com.cool.todayheadline.vo.NewsItem;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 用法：new DownloadTask(recyclerView,mListener,activity).execute(urls);
 */

public class PreDownloadTask extends AsyncTask<String,Object,Sys>{

    private List<NewsItem> newsItemList;
    private Activity activity;

    private static final String TAG = "PreDownloadTask";

    public PreDownloadTask(List<NewsItem> newsItemList,
                           Activity activity){
        this.newsItemList=newsItemList;
        this.activity=activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Sys doInBackground(String... strings) {
        String url=(String)strings[0];
        Sys sys=null;
        try{
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    .url(url)
                    .build();
            Response response=client.newCall(request).execute();
            String responseData=response.body().string();
            Gson g=new Gson();
            sys =g.fromJson(responseData,Sys.class);

        }catch (Exception e){
            e.printStackTrace();
        }
        return sys;
    }

    @Override
    protected void onPostExecute(Sys s) {
        super.onPostExecute(s);

        if (s==null||"".equals(s.toString()))
        {
            List<Cache_NewsItem> cacheNewsItems=PreferenceNewsUtil.cache_findAllNews();
            if(cacheNewsItems!=null){
                newsItemList=AssemblerUtil.CacheTableTONewsItem(cacheNewsItems);

                Toast.makeText(activity, "连接服务器失败，为您呈现缓存数据", Toast.LENGTH_LONG);

            }else{
                Toast.makeText(activity,"连接服务器失败,并且无缓存数据",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            int total=s.getResult().getData().length;

            for(int i=0;i<total;i++){
                NewsItem newsItem;
                newsItem=AssemblerUtil.transform(s,i);
                newsItemList.add(newsItem);
            }
            if(!"头条".equals(s.getResult().getData()[0].getCategory())){
                //开启前台通知Service
                Intent intent=new Intent(activity.getApplication(), NewsNotificationService.class);
                intent.putExtra("URL",s.getResult().getData()[0].getUrl());
                intent.putExtra("title",s.getResult().getData()[0].getTitle());
                activity.startService(intent);
            }

            //缓存新闻信息
            List<Cache_NewsItem> cacheNewsItems=AssemblerUtil.NewsItemToCacheTable(newsItemList);
            for(Cache_NewsItem cacheNewsItem:cacheNewsItems){
                PreferenceNewsUtil.cache_insertNews(cacheNewsItem);
            }

        }
    }
}
