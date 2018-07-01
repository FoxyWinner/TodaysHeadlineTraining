package com.cool.todayheadline.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.cool.todayheadline.R;
import com.cool.todayheadline.fragments.LoginFragment;
import com.cool.todayheadline.fragments.RegisterFragment;

import java.util.ArrayList;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {


// UI references.

    private ViewPager viewPager;
    private FragmentPagerAdapter mAdapter;
    //   private List<Fragment> mFragments;
    private Fragment loginFragment;
    private Fragment registerFragment;
    ArrayList<Fragment> mFragments=new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        findViewById();



    }
    private void findViewById(){
        viewPager=(ViewPager) findViewById(R.id.id_viewpager);
        initFragment();
    }
    private void initFragment(){

        loginFragment=new LoginFragment();
        registerFragment=new RegisterFragment();
        mFragments.add(loginFragment);
        mFragments.add(registerFragment);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {

            @Override
            public int getCount()
            {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0)
            {
                return mFragments.get(arg0);
            }
        };

        viewPager.setAdapter(mAdapter);

        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                switch(arg0){
                    case 0:
                        //滑动至第一页处理逻辑
                        break;
                    case 1:
                        //滑动至第二页处理逻辑
                        break;
                }
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }






}

