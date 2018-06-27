package com.cool.todayheadline.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;

import com.cool.todayheadline.R;
import com.cool.todayheadline.extend.MainActivityViewPagerAdapter;
import com.cool.todayheadline.extend.NoSlidingViewPaper;
import com.cool.todayheadline.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity
{


    private NoSlidingViewPaper mpager;
    private MainActivityViewPagerAdapter mainActivityViewPagerAdapter;
    private BottomNavigationView mNavigation;

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
        setContentView(R.layout.activity_welcome);

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
//        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mpager = (NoSlidingViewPaper) findViewById(R.id.vp_main_container);
    }

    public void init()
    {
        //将bar隐藏



        //为底部导航栏添加监听事件
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mainActivityViewPagerAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());

//        为Adapter添加Fragment
//        mainActivityViewPagerAdapter.addFragment(new HomeFragment());
//        mainActivityViewPagerAdapter.addFragment(new FavoriteFragment());
        mainActivityViewPagerAdapter.addFragment(new SettingsFragment());
        mpager.setAdapter(mainActivityViewPagerAdapter);



    }





}
