package com.cool.todayheadline.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.cool.todayheadline.R;

import java.io.InputStream;

//执行ex：new DownloadImageTask(QRImg).execute(URL);

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
{
        private ImageView bmImage;
        private static final String TAG = "DownloadImageTask";
        private Context mContext;
        private String urldisplay;

        public DownloadImageTask(ImageView bmImage,Context mContext)
        {
        this.bmImage = bmImage;
        this.mContext = mContext;
        }

        protected Bitmap doInBackground(String... urls)
        {
                urldisplay = urls[0];
                //尝试从缓存中取出bitmap
                ImgCacheUtil.initDiskLruCache(mContext, "CacheDir");
                Bitmap bitmap = ImgCacheUtil.getCache(urldisplay);

                if (bitmap == null)
                {
                        try
                        {
                                InputStream in = new java.net.URL(urldisplay).openStream();

                                ImgCacheUtil.cacheImage(in, urldisplay);

                                //这个地方能不能直接拿bitmap进行缓存
                                InputStream in2 = new java.net.URL(urldisplay).openStream();
                                bitmap = BitmapFactory.decodeStream(in2);

                        } catch (Exception e)
                        {
                                Log.e("Error", e.getMessage());
                                e.printStackTrace();
                        }
                }

                return bitmap;
        }

        protected void onPostExecute(Bitmap result)
        {

                super.onPostExecute(result);
                if (result == null || result.equals("")) {
                        result = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.img_loading_fail);
                        bmImage.setImageBitmap(result);
                }
                else
                {

                        //todo:为什么在模拟器上会图片缩小
                        bmImage.setImageBitmap(result);
                }

        }



}
