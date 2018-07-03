package com.cool.todayheadline.activities;

import android.content.Intent;
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

public class WelcomeActivity extends AppCompatActivity
{
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


        handler.sendEmptyMessageDelayed(0,3000);
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    public void getHome(){
        Bundle bundle = new Bundle();
        bundle.putSerializable(Const.NEWS_ITEM_LIST, (Serializable) newsItemList);
        Intent intent = new Intent(WelcomeActivity.this, PreferenceActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
        finish();
    }
}
