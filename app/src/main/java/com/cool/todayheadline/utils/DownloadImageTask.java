package com.cool.todayheadline.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

//执行ex：new DownloadImageTask(QRImg).execute(URL);

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
{
        ImageView bmImage;
        private static final String TAG = "DownloadImageTask";

        public DownloadImageTask(ImageView bmImage)
        {
        this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls)
        {
                String urldisplay = urls[0];
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
                        Log.w(TAG, "onPostExecute:进入result查询失败分支");
                }
                bmImage.setImageBitmap(result);
        }
}
