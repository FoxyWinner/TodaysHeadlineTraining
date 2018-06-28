package com.cool.todayheadline.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.cool.todayheadline.bean.Sys;
import com.cool.todayheadline.vo.NewsItem;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 用法：new DownloadTask(newsItemList).execute(urls);
 */

public class DownloadTask extends AsyncTask<String,Object,Sys>{

    NewsItem newsItem[];

    private static final String TAG = "DownloadTask";

    public DownloadTask(NewsItem[] newsItem){
        this.newsItem=newsItem;
    }


    @Override
    protected Sys doInBackground(String... strings) {
        String url=(String)strings[0];
        Sys sys=new Sys();
        try{
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    .url(url)
                    .build();
            Response response=client.newCall(request).execute();
            String responseData=response.body().string();
            Log.d(TAG, responseData);
            Gson g=new Gson();
            sys =g.fromJson(responseData,Sys.class);
            while(true){
                if(sys!=null)
                    break;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return sys;
    }

    @Override
    protected void onPostExecute(Sys s) {
        super.onPostExecute(s);
        int total=s.getResult().getData().length;
        NewsItem[] news=new NewsItem[total];
        for(int i=0;i<total;i++){
            news[i]=AssemblerUtil.transform(s,i);
        }
        newsItem=news;
    }
}
