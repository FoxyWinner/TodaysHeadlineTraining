package com.cool.todayheadline.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImgCacheUtil
{
    private static final String TAG = "ImgCacheUtil";
    private static DiskLruCache diskLruCache;

    public static boolean cacheImage(InputStream inputStream,String imgUrl)
    {
        return cacheImg(inputStream,imgUrl);
    }


    /**
     * 初始化DiskLruCache，并使用DiskLruCache.Editor准备缓存：
     */
    public static void initDiskLruCache(Context context, String uniqueName)
    {
        if(diskLruCache == null || diskLruCache.isClosed())
        {
            try
            {
                final String cachePath = context.getCacheDir().getPath();
                File cacheDir = new File(cachePath + File.separator + uniqueName);

                if (!cacheDir.exists())
                {
                    cacheDir.mkdirs();
                }
                //初始化DiskLruCache

                diskLruCache = DiskLruCache.open(cacheDir, PackageInfoUtil.getVersionCode(context), 1, Const.CACHE_MAX_SIZE);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static boolean cacheImg(InputStream inputStream, String imgUrl)
    {
        try
        {
            String key = hashKeyForDisk(imgUrl);
            DiskLruCache.Snapshot snapShot = diskLruCache.get(key);
            if (snapShot == null)//如果缓存中没有该图片，进行缓存
            {
                //得到DiskLruCache.Editor
                DiskLruCache.Editor editor = diskLruCache.edit(key);
                if (editor != null)
                {
                    OutputStream outputStream = editor.newOutputStream(0);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                    int b;
                    //将图片输入流写入缓存
                    while ((b = inputStream.read()) != -1)
                    {
                        bufferedOutputStream.write(b);
                    }

                    //一定要关闭输入输出流，不然造成bitmapdecode失败。
                    inputStream.close();
                    bufferedOutputStream.close();
                    editor.commit();
                }
            }

            diskLruCache.flush();
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 把图片URL经过MD5加密生成唯一的key值，避免了URL中可能含有非法字符问题，hashKeyForDisk()代码如下：
     * @param key
     * @return
     */
    public static String hashKeyForDisk(String key)
    {
        String cacheKey;
        try
        {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e)
        {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes)
    {
        // http://stackoverflow.com/questions/332079
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++)
        {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1)
            {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    public static Bitmap getCache(String imgUrl)
    {
        Bitmap bitmap;
        try {
            String key = hashKeyForDisk(imgUrl);
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
            if (snapshot != null)
            {
                Log.d(TAG, "getCache: 进入到了snapshot != null");
                InputStream in = snapshot.getInputStream(0);

                bitmap = BitmapFactory.decodeStream(in);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
