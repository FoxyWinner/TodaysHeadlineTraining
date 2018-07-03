package com.cool.todayheadline.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cool.todayheadline.Services.NewsNotificationService;
import com.cool.todayheadline.adapters.MyNewsItemRecyclerViewAdapter;
import com.cool.todayheadline.bean.Cache_NewsItem;
import com.cool.todayheadline.bean.Sys;
import com.cool.todayheadline.vo.NewsItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 用法：new DownloadTask(recyclerView,mListener,activity).execute(urls);
 */

public class DownloadTask extends AsyncTask<String,Object,Sys>{

    RecyclerView recyclerView;
    Activity activity;

    private static final String TAG = "DownloadTask";

    public DownloadTask(RecyclerView recyclerView,
                        Activity activity){
        this.recyclerView=recyclerView;
        this.activity=activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!new UIHelper().isShowing())
        {
            new UIHelper().showDialogForLoading(activity, "正在加载...", true);
        }
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
    protected void onPostExecute(Sys s) {
        super.onPostExecute(s);

        if (s==null||"".equals(s.toString()))
        {
            //服务器连接失败
            List<Cache_NewsItem> cacheNewsItems=PreferenceNewsUtil.cache_findAllNews();
            if(cacheNewsItems!=null){
                //有缓存数据时
                List<NewsItem> newsItemList=AssemblerUtil.CacheTableTONewsItem(cacheNewsItems);
                recyclerView.setAdapter(new MyNewsItemRecyclerViewAdapter(activity,recyclerView,newsItemList));
                new UIHelper().hideDialogForLoading();
                Snackbar sb = Snackbar.make(recyclerView, "连接服务器失败，这是缓存数据", Snackbar.LENGTH_LONG);
                sb .setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                sb.show();
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
            new UIHelper().hideDialogForLoading();
            recyclerView.setAdapter(new MyNewsItemRecyclerViewAdapter(activity,recyclerView,newsItemList));
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
