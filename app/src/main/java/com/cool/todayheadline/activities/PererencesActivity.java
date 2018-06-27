package com.cool.todayheadline.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.cool.todayheadline.R;
import com.cool.todayheadline.adapters.ImageAdapter;


public class PererencesActivity extends AppCompatActivity {

    GridView gridView;
    ImageAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view= this.getLayoutInflater().inflate((R.layout.activity_pererences), null);

        GridView gridView = view.findViewById(R.id.gridView);


        gridView =(GridView) findViewById(R.id.gridView);
        adapter =new ImageAdapter(this);
        gridView.setAdapter(adapter);




        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        handler.sendEmptyMessageDelayed(0,5000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    public void getHome(){
        Intent intent = new Intent(PererencesActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }





    }

