package com.cool.todayheadline.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.cool.todayheadline.R;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.utils.Info;
import com.cool.todayheadline.utils.PreDownloadTask;
import com.cool.todayheadline.vo.NewsItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private static final int TIME=3000;
    private static final int GO_MAIN=100;
    private static final int GO_GUIDE=101;

    Handler mhandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_MAIN:
                    goMainActivity();
                    break;
                case GO_GUIDE:
                    goPreferenceActivity();
                    break;
            }
        }
    };

    private List<NewsItem> newsItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }


        //预加载数据，HomeFragment的数据。

        String url= Info.path_queryNewsItems("");
        String[] urls={url};

        //预加载数据到newsItemList，但 如果加载不成功，也就是个空了。
        new PreDownloadTask(newsItemList,this).execute(urls);


        mhandler.sendEmptyMessageDelayed(0,3000);
        init();
    }
    private void init()
    {
        //判断是否是第一次进入
        SharedPreferences sf = getSharedPreferences("data", MODE_PRIVATE);
        boolean isFirstIn = sf.getBoolean("isFirstIn", true);
        SharedPreferences.Editor editor = sf.edit();
        if (isFirstIn) {     //若为true，则是第一次进入
            editor.putBoolean("isFirstIn", false);
            mhandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);//将欢迎页停留5秒，并且将message设置为跳转到
            //                                                        引导页SplashActivity，跳转在goGuide中实现
        }
        else{
                mhandler.sendEmptyMessageDelayed(GO_MAIN, TIME);//将欢迎页停留5秒，并且将message设置文跳转到                                                                   MainActivity，跳转功能在goMain中实现
            }
            editor.commit();
        }

    private void goMainActivity()
    {
        //直接进入main
        Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Const.NEWS_ITEM_LIST, (Serializable) newsItemList);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }
    private void goPreferenceActivity()
    {
        //
        Intent intent = new Intent(WelcomeActivity.this, PreferenceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Const.NEWS_ITEM_LIST, (Serializable) newsItemList);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
