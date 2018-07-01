package com.cool.todayheadline.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.cool.todayheadline.R;
import com.cool.todayheadline.bean.NewsItem_table;
import com.cool.todayheadline.fragments.HomeFragment;
import com.cool.todayheadline.fragments.MyNewsItemRecyclerViewAdapter;
import com.cool.todayheadline.utils.AssemblerUtil;
import com.cool.todayheadline.utils.PreferenceNewsUtil;
import com.cool.todayheadline.vo.NewsItem;

import java.util.List;

public class NewsCollector extends AppCompatActivity{
    private HomeFragment.OnListFragmentInteractionListener mListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_collector);
        Context context = this;

        RecyclerView recyclerView = findViewById(R.id.list);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        List<NewsItem_table> newsItem_tables= PreferenceNewsUtil.findAllNews();
        List<NewsItem> newsItemList= AssemblerUtil.tableToNews(newsItem_tables);
        recyclerView.setAdapter(new MyNewsItemRecyclerViewAdapter(context,recyclerView,newsItemList, mListener));

    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
