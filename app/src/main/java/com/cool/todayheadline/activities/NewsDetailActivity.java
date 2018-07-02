package com.cool.todayheadline.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cool.todayheadline.R;
import com.cool.todayheadline.utils.TSnackBarUtil;
import com.cool.todayheadline.utils.UIHelper;

public class NewsDetailActivity extends AppCompatActivity
{
    private WebView mNewsWebView;
    FloatingActionButton mFab;
    Toolbar mToolbar;

    private static String PARAM_URL = "NEWS_DETAIL_URL";


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




        mFab = (FloatingActionButton) findViewById(R.id.news_detail_fab);
        mFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                TSnackBarUtil.showTBar(view,"收藏todo");
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



        Bundle bundle = this.getIntent().getExtras();

        if(bundle!=null)
        {
            mNewsWebView.loadUrl((String) bundle.get(PARAM_URL));
        }



    }
}
