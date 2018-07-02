package com.cool.todayheadline.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.cool.todayheadline.R;
import com.cool.todayheadline.activities.MainActivity;
import com.cool.todayheadline.activities.NewsDetailActivity;
import com.cool.todayheadline.utils.Const;

public class NewsNotificationService extends Service {

    private static final String TAG = "NewsNotificationService";

    private static final int PUSH_NOTIFICATION_ID = (0x001);
    private static final String PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID";
    private static final String PUSH_CHANNEL_NAME = "PUSH_NOTIFY_NAME";

    private static String PARAM_URL = "NEWS_DETAIL_URL";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String URL=intent.getStringExtra("URL");
        Const.REAL_URL=URL;
        String Title=intent.getStringExtra("title");
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(PUSH_CHANNEL_ID, PUSH_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
        Intent intent1=new Intent();
        intent1.setClass(this, NewsDetailActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0, intent1,0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,PUSH_CHANNEL_ID);
                builder.setContentTitle("您偏好的新闻有更新")
                .setContentText(Title)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_app_logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_app_logo))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        Notification notification = builder.build();
        startForeground(PUSH_NOTIFICATION_ID,notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
