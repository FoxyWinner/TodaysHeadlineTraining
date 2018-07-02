package com.cool.todayheadline.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.cool.todayheadline.R;
import com.cool.todayheadline.adapters.MyCollectorItemRecyclerViewAdapter;
import com.cool.todayheadline.bean.NewsItem_table;
import com.cool.todayheadline.utils.AssemblerUtil;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.utils.PreferenceNewsUtil;
import com.cool.todayheadline.vo.NewsItem;

import java.util.List;

public class NewsCollectorActivity extends AppCompatActivity{
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_collector);
        Context context = this;

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


        RecyclerView recyclerView = findViewById(R.id.list);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        List<NewsItem_table> newsItem_tables= PreferenceNewsUtil.findAllNews(Const.USER_ID+"");
        List<NewsItem> newsItemList= AssemblerUtil.tableToNews(newsItem_tables);
        recyclerView.setAdapter(new MyCollectorItemRecyclerViewAdapter(context,recyclerView,newsItemList));

    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
