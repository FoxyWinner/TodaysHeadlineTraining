package com.cool.todayheadline.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
 * 登陆界面@author李赫
 */
public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener
{
    private ViewPager viewPager;
    private FragmentPagerAdapter mAdapter;

    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.hide();
        }

        findViewById();
    }

    private void findViewById()
    {
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        initFragment();

    }

    private void initFragment()
    {

        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
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
    }


    @Override
    public void onFragmentInteraction()
    {
        viewPager.setCurrentItem(1);
    }


}

