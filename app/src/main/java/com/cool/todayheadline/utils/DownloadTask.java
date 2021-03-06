package com.cool.todayheadline.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cool.todayheadline.adapters.MyNewsItemRecyclerViewAdapter;
import com.cool.todayheadline.bean.Cache_NewsItem;
import com.cool.todayheadline.bean.Sys;
import com.cool.todayheadline.services.NewsNotificationService;
import com.cool.todayheadline.vo.NewsItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 用法：new DownloadTask(recyclerView,activity).execute(urls);
 */

public class DownloadTask extends AsyncTask<String,Object,Sys>{

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private Activity activity;
    private String categroy;

    private static final String TAG = "DownloadTask";

    public DownloadTask(SwipeRefreshLayout refreshLayout,RecyclerView recyclerView,
                        Activity activity,String categroy)
    {
        this.refreshLayout = refreshLayout;
        this.recyclerView = recyclerView;
        this.activity = activity;
        this.categroy = categroy;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        refreshLayout.setRefreshing(true);
    }

    @Override
    protected Sys doInBackground(String... strings) {
        String url=strings[0];
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
    protected void onPostExecute(Sys s)
    {
        super.onPostExecute(s);

        if (s==null||"".equals(s.toString()))
        {
            //服务器连接失败
            List<Cache_NewsItem> cacheNewsItems=PreferenceNewsUtil.cache_findAllNewsByCategory(categroy);
            if(cacheNewsItems!=null){
                //有缓存数据时
                List<NewsItem> newsItemList=AssemblerUtil.CacheTableTONewsItem(cacheNewsItems);
                recyclerView.setAdapter(new MyNewsItemRecyclerViewAdapter(activity,recyclerView,newsItemList));
                new UIHelper().hideDialogForLoading();
                Toast.makeText(activity,"连接服务器失败，这是缓存数据",Toast.LENGTH_SHORT).show();
            }else{
                //无缓存数据
                Toast.makeText(activity,"连接服务器失败,并且无缓存数据",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            //获取数据成功并更新UI
            int total=s.getResult().getData().length;
            List<NewsItem> newsItemList=new ArrayList<NewsItem>();
            for(int i=0;i<total;i++){
                NewsItem newsItem;
                newsItem=AssemblerUtil.transform(s,i);
                newsItemList.add(newsItem);
            }
            recyclerView.setAdapter(new MyNewsItemRecyclerViewAdapter(activity,recyclerView,newsItemList));
            new UIHelper().hideDialogForLoading();
            if(!"头条".equals(s.getResult().getData()[0].getCategory())){
                //开启前台通知Service
                Intent intent=new Intent(activity.getApplication(), NewsNotificationService.class);
                intent.putExtra("URL",s.getResult().getData()[0].getUrl());
                intent.putExtra("title",s.getResult().getData()[0].getTitle());
                activity.startService(intent);
            }
            //缓存新闻信息
            List<Cache_NewsItem> cacheNewsItems=AssemblerUtil.NewsItemToCacheTable(newsItemList);
            PreferenceNewsUtil.cache_inserNews(cacheNewsItems);
            ClearCacheThread.clearCache(s);
        }
        refreshLayout.setRefreshing(false);
    }
}
