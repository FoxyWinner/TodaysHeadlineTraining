package com.cool.todayheadline.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 在PreDownloadTask的onPost方法里执行。
 * PreDownloadTask是将整个HomeFragment页面的list（除了图片）加载出来。
 * 而本类是根据之前加载好的list里的picurl逐条缓存图片，当welcome跳转后，缓存中止，没有缓存完的图片在HomeFragment里加载
 */

public class PreDownloadImageTask extends AsyncTask<String, Void, Void>
{
    private static final String TAG = "PreDownloadImageTask";
    private Context mContext;
    private List<String> urls = new ArrayList<>();

    public PreDownloadImageTask(List<String> urls, Context mContext)
    {
        this.urls= urls;
        this.mContext = mContext;
    }

    protected Void doInBackground(String... args)
    {
        ImgCacheUtil.initDiskLruCache(mContext, "CacheDir");
        Log.w(TAG, "doInBackground: "+urls.size() );
        for (int i = 0; i < urls.size(); i++)
        {
            //如果界面已经跳转，cancel异步任务
            if(Const.HAS_JUMP)
            {
                Log.w(TAG, "doInBackground: 预加载图片线程已关闭" );
                return null;
            }

            String imgUrl = this.urls.get(i);

            //snapshot==null说明未被缓存过
            DiskLruCache.Snapshot snapshot = ImgCacheUtil.isCached(imgUrl);

            //需要进行缓存
            if (snapshot == null)
            {
                try
                {
                    InputStream in = new java.net.URL(imgUrl).openStream();
                    ImgCacheUtil.cacheImage(in, imgUrl);
                    in.close();
                    Log.w(TAG, "doInBackground: "+i+"预加载图片"+imgUrl );
                } catch (Exception e)
                {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                }
            }
            else
            {
                Log.w(TAG, "doInBackground: "+i+"已有缓存，取消预加载"+imgUrl );
            }
        }

        return null;
    }

    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
    }
}
