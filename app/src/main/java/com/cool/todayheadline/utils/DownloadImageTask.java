package com.cool.todayheadline.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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

    public DownloadImageTask(ImageView bmImage, Context mContext)
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
                bitmap = BitmapFactory.decodeStream(in);

                //这个地方不能直接拿bitmap进行缓存（牵扯到DiskLruCache的实现），
                // 然而inputstream也的确不能重复读写直接
                //然而我们又不想拿到两次网络输入流，就直接把bitmap转化为InputStream吧
                ImgCacheUtil.cacheImage(CommonUtil.convertBitmapToIS(bitmap), urldisplay);
                in.close();

            } catch (Exception e)
            {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
        }else
        {
//            Log.d(TAG, "doInBackground: 使用缓存加载图片");
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result)
    {

        super.onPostExecute(result);
        if (result == null || result.equals(""))
        {
            result = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.img_loading_fail);
            bmImage.setImageBitmap(result);
        }
        else
        {
            bmImage.setImageBitmap(result);
        }

    }

    /**
     * @param bitmap 对象
     * @param w 要缩放的宽度
     * @param h 要缩放的高度
     * @return newBmp 新 Bitmap对象
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newBmp;
    }


}
