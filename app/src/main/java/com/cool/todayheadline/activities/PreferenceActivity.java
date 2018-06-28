package com.cool.todayheadline.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cool.todayheadline.R;
import com.cool.todayheadline.adapters.ImageAdapter;
import com.cool.todayheadline.fragments.FavoriteFragment;
import com.cool.todayheadline.utils.Const;


public class PreferenceActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

    private GridView    gridView;
    private ImageAdapter    adapter;
    FavoriteFragment favoriteFragment = new FavoriteFragment();

    private String[] names = {"shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pererences);

        gridView =(GridView) findViewById(R.id.gridView);
        adapter =new ImageAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(PreferenceActivity.this);


        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        handler.sendEmptyMessageDelayed(0,5000);





    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
          //  getHome();
            super.handleMessage(msg);
        }
    };
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent intent = new Intent(this,MainActivity.class);

        intent.putExtra(Const.USER_PREFERENCE,names[position]);

        startActivity(intent);
        finish();
    }






    }

