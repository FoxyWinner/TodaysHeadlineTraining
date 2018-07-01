package com.cool.todayheadline.activities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.cool.todayheadline.R;
import com.cool.todayheadline.adapters.MyCollectorItemRecyclerViewAdapter;
import com.cool.todayheadline.bean.NewsItem_table;
import com.cool.todayheadline.fragments.HomeFragment;
import com.cool.todayheadline.utils.AssemblerUtil;
import com.cool.todayheadline.utils.PreferenceNewsUtil;
import com.cool.todayheadline.vo.NewsItem;

import java.util.List;

public class NewsCollectorActivity extends AppCompatActivity{
    private HomeFragment.OnListFragmentInteractionListener mListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_collector);
        Context context = this;

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            Resources resources = this.getResources();
            Drawable bgColorDrawable = resources.getDrawable(R.drawable.news_detail_bar_bg);
            actionBar.setBackgroundDrawable(bgColorDrawable);
            actionBar.setTitle("我的收藏");

//            actionBar.setLogo(R.mipmap.ic_app_logo);
//            actionBar.setDisplayUseLogoEnabled(true);
//            actionBar.setDisplayShowHomeEnabled(true);
        }


        RecyclerView recyclerView = findViewById(R.id.list);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        List<NewsItem_table> newsItem_tables= PreferenceNewsUtil.findAllNews();
        List<NewsItem> newsItemList= AssemblerUtil.tableToNews(newsItem_tables);
        recyclerView.setAdapter(new MyCollectorItemRecyclerViewAdapter(context,recyclerView,newsItemList, mListener));

    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
