package com.cool.todayheadline.activities;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.cool.todayheadline.R;

public class NewsDetailActivity extends AppCompatActivity
{
    private WebView mNewsWebView;
    private static String PARAM_URL = "NEWS_DETAIL_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            Resources resources = this.getResources();
            Drawable bgColorDrawable = resources.getDrawable(R.drawable.news_detail_bar_bg);
            actionBar.setBackgroundDrawable(bgColorDrawable);
            //todo:这句话是无效的，怎样给actionbar设置图标？
            actionBar.setIcon(R.mipmap.ic_app_logo);
            actionBar.setTitle("新闻");
        }

        mNewsWebView = findViewById(R.id.news_detail_wv);

        Bundle bundle = this.getIntent().getExtras();

        if(bundle!=null)
        {
            mNewsWebView.loadUrl((String) bundle.get(PARAM_URL));
        }


    }
}
