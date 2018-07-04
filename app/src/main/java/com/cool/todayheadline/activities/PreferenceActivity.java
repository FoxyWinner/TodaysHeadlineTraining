package com.cool.todayheadline.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cool.todayheadline.R;
import com.cool.todayheadline.adapters.ImageAdapter;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.vo.NewsItem;

import java.util.ArrayList;
import java.util.List;


public class PreferenceActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private GridView gridView;
    private ImageAdapter adapter;
    private SharedPreferences sharedPreferences;


    //存放欢迎页传来的数据
    private List<NewsItem> newsItemList = new ArrayList<>();

    private String[] names = {"shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preferences);

        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new ImageAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(PreferenceActivity.this);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.hide();
        }


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(this, MainActivity.class);

        sharedPreferences = getSharedPreferences(Const.SP_USER_PREFERENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(Const.USER_PREFERENCE, names[position]);

        edit.commit();

        //无需开包，直接将预加载数据传给下一位
        intent.putExtras(getIntent().getExtras());

        startActivity(intent);
        finish();
    }
}

