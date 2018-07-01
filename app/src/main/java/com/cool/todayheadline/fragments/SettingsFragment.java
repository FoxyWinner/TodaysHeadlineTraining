package com.cool.todayheadline.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cool.todayheadline.R;
import com.cool.todayheadline.activities.LoginActivity;
import com.cool.todayheadline.activities.NewsCollectorActivity;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.utils.PreferenceNewsUtil;

public class SettingsFragment extends Fragment
{

    private RelativeLayout relativeLayout;
    private Button newsCollector;
    private Button clearCache;

    private TextView textView;


    public SettingsFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        textView = (TextView)view.findViewById(R.id.tv_username);

        textView.setText(Const.USER_NAME);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.re_myinfo);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        newsCollector=view.findViewById(R.id.bt_opinion);
        newsCollector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsCollectorActivity.class);
                startActivity(intent);
            }
        });
        clearCache=view.findViewById(R.id.bt_aboutus);
        clearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceNewsUtil.deleteUserAllNews(Const.USER_ID+"");
                Toast.makeText(getActivity(),"清理完成",Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

}
