package com.cool.todayheadline.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cool.todayheadline.R;
import com.cool.todayheadline.Services.NewsNotificationService;
import com.cool.todayheadline.bean.NewsItem_table;
import com.cool.todayheadline.utils.AssemblerUtil;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.utils.PreferenceNewsUtil;
import com.cool.todayheadline.utils.TSnackBarUtil;
import com.cool.todayheadline.utils.UIHelper;
import com.cool.todayheadline.vo.NewsItem;

public class NewsDetailActivity extends AppCompatActivity
{
    private WebView mNewsWebView;
    FloatingActionButton mFab;
    Toolbar mToolbar;
    NewsItem newsItem;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);


        //set toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//返回
            }
        });

        //todo:如何改变toolbar title的内容？
//        CommonUtil.addMiddleTitle(this,"新闻详情",mToolbar);



        newsItem = (NewsItem) this.getIntent().getSerializableExtra(Const.PARAM_VO);


        mFab = (FloatingActionButton) findViewById(R.id.news_detail_fab);
        mFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                NewsItem_table newsItem_table = AssemblerUtil.transformToPOJO(newsItem);
                if (PreferenceNewsUtil.insertNews(newsItem_table))
                {
                    TSnackBarUtil.showTBar(view," 收藏成功");
                }
                else
                {
                    TSnackBarUtil.showTBar(view," 收藏失败，请检查是否重复收藏");
                }
            }
        });

        mNewsWebView = findViewById(R.id.news_detail_wv);
        //允许JS加载，垃圾广告全出来了
        mNewsWebView.getSettings().setJavaScriptEnabled(true);
        mNewsWebView.setWebViewClient(new WebViewClient(){
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view,url,favicon);

                if(!new UIHelper().isShowing())
                {
                    new UIHelper().showDialogForLoading(NewsDetailActivity.this, "正在加载...", true);
                }
            }

            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view,url);
                new UIHelper().hideDialogForLoading();
            }

        });




        if(newsItem!=null)
        {
            mNewsWebView.loadUrl(newsItem.getUrl());
        }
        //通过通知栏启动NewsDetail界面时，避免URL传不过来 @author 柳斌辉
        else{
            mNewsWebView.loadUrl(Const.REAL_URL);
            Intent intent=new Intent(getApplication(), NewsNotificationService.class);
            stopService(intent);
        }






    }
}
