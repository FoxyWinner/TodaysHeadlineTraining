package com.cool.todayheadline.utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cool.todayheadline.bean.Cache_NewsItem;
import com.cool.todayheadline.bean.Sys;
import com.cool.todayheadline.vo.NewsItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PreDownloadTask extends AsyncTask<String, Object, Sys>
{

    private List<NewsItem> newsItemList;
    private Activity activity;

    private static final String TAG = "PreDownloadTask";

    public PreDownloadTask(List<NewsItem> newsItemList,
                           Activity activity)
    {
        this.newsItemList = newsItemList;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected Sys doInBackground(String... strings)
    {
        String url = (String) strings[0];
        Sys sys = null;
        try
        {
            OkHttpClient client=new OkHttpClient.Builder()
                    .connectTimeout(3, TimeUnit.SECONDS)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            Gson g = new Gson();
            sys = g.fromJson(responseData, Sys.class);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return sys;
    }

    @Override
    protected void onPostExecute(Sys s)
    {
        super.onPostExecute(s);

        if (s == null || "".equals(s.toString()))
        {
            List<Cache_NewsItem> cacheNewsItems = PreferenceNewsUtil.cache_findAllNews();
            if (cacheNewsItems != null)
            {
                newsItemList = AssemblerUtil.CacheTableTONewsItem(cacheNewsItems);

                Toast.makeText(activity, "连接服务器失败，为您呈现缓存数据", Toast.LENGTH_LONG);

            }
            else
            {
                Toast.makeText(activity, "连接服务器失败,并且无缓存数据", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            int total = s.getResult().getData().length;

            List<String> urls = new ArrayList<>();
            for (int i = 0; i < total; i++)
            {
                NewsItem newsItem;
                newsItem = AssemblerUtil.transform(s, i);
                urls.add(newsItem.getPic_url());
                newsItemList.add(newsItem);
            }

            new PreDownloadImageTask(urls,activity).execute("");

            //缓存新闻信息
            List<Cache_NewsItem> cacheNewsItems = AssemblerUtil.NewsItemToCacheTable(newsItemList);
            PreferenceNewsUtil.cache_inserNews(cacheNewsItems);

        }
    }
}
