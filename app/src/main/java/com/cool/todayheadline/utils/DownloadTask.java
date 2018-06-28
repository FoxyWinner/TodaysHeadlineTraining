package com.cool.todayheadline.utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cool.todayheadline.bean.Sys;
import com.cool.todayheadline.fragments.HomeFragment;
import com.cool.todayheadline.fragments.MyNewsItemRecyclerViewAdapter;
import com.cool.todayheadline.vo.NewsItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 用法：new DownloadTask(newsItemList).execute(urls);
 */

public class DownloadTask extends AsyncTask<String,Object,Sys>{

    RecyclerView recyclerView;
    HomeFragment.OnListFragmentInteractionListener mListener;
    Activity activity;

    private static final String TAG = "DownloadTask";

    public DownloadTask(RecyclerView recyclerView,
                        HomeFragment.OnListFragmentInteractionListener mListener,
                        Activity activity){
        this.recyclerView=recyclerView;
        this.mListener=mListener;
        this.activity=activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        new UIHelper().showDialogForLoading(activity, "正在加载...", true);
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
        Log.e(TAG, "onPostExecute: "+total );
        List<NewsItem> newsItemList=new ArrayList<NewsItem>();
        for(int i=0;i<total;i++){
            NewsItem newsItem=new NewsItem();
            newsItem=AssemblerUtil.transform(s,i);
            newsItemList.add(newsItem);
        }
        new UIHelper().hideDialogForLoading();
        recyclerView.setAdapter(new MyNewsItemRecyclerViewAdapter(activity,newsItemList, mListener));
    }
}
