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
                Bitmap bitmap = null;
                try
                {
                        InputStream in = new java.net.URL(urldisplay).openStream();
                        bitmap = BitmapFactory.decodeStream(in);
                } catch (Exception e)
                {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                }
                return bitmap;
        }

        protected void onPostExecute(Bitmap result)
        {
                super.onPostExecute(result);
                if (result == null || result.equals("")) {
                        result = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.img_loading_fail);
                        bmImage.setImageBitmap(result);
                        bmImage.setTag("");
                }
                else
                {
                        bmImage.setImageBitmap(result);
                        bmImage.setTag(urldisplay);
//                        Log.d(TAG, "onPostExecute: bmImage.tag="+bmImage.getTag());
                }

        }
}
