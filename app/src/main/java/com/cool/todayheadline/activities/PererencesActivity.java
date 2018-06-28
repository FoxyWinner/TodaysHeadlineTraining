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


public class PererencesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private GridView    gridView;
    private ImageAdapter    adapter;
    FavoriteFragment favoriteFragment = new FavoriteFragment();

    private String[] names = {"社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pererences);

        gridView =(GridView) findViewById(R.id.gridView);
        adapter =new ImageAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(PererencesActivity.this);


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
        intent.putExtra("per",names[position]);

       // Log.e("!!!!!",names[position]);
            startActivity(intent);
        finish();
    }






    }

