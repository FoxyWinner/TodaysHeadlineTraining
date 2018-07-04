package com.cool.todayheadline.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;

import com.cool.todayheadline.R;
import com.cool.todayheadline.extend.MainActivityViewPagerAdapter;
import com.cool.todayheadline.extend.NoSlidingViewPaper;
import com.cool.todayheadline.fragments.NewsItemsFragment;
import com.cool.todayheadline.fragments.SettingsFragment;
import com.cool.todayheadline.utils.Const;

public class MainActivity extends AppCompatActivity
{


    private NoSlidingViewPaper mpager;
    private MainActivityViewPagerAdapter mainActivityViewPagerAdapter;
    private BottomNavigationView mNavigation;
    private String userPreference;
    private static final String TAG = "MainActivity";


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    mpager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    mpager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mpager.setCurrentItem(2);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        setContentView(R.layout.activity_main);
        findView();
        init();

    }

    public void findView()
    {
        mNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        mpager = (NoSlidingViewPaper) findViewById(R.id.vp_main_container);
    }

    public void init()
    {
        //设置预加载3个界面
        mpager.setOffscreenPageLimit(3);
        //为底部导航栏添加监听事件
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mainActivityViewPagerAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());

        SharedPreferences sharedPreferences = this.getSharedPreferences(Const.SP_USER_PREFERENCE, Context.MODE_PRIVATE);
        userPreference = sharedPreferences.getString(Const.USER_PREFERENCE, "Null");

        Log.d(TAG, "init:userPreference "+userPreference);

//        为Adapter添加Fragment
        mainActivityViewPagerAdapter.addFragment(NewsItemsFragment.newInstance(""));
        mainActivityViewPagerAdapter.addFragment(NewsItemsFragment.newInstance(userPreference));
        mainActivityViewPagerAdapter.addFragment(new SettingsFragment());
        mpager.setAdapter(mainActivityViewPagerAdapter);
    }





}
