package com.cool.todayheadline.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cool.todayheadline.R;
import com.cool.todayheadline.activities.InfoActivity;
import com.cool.todayheadline.activities.LoginActivity;
import com.cool.todayheadline.activities.NewsCollectorActivity;
import com.cool.todayheadline.activities.PreferenceActivity;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.utils.ImgCacheUtil;
import com.cool.todayheadline.utils.PreferenceNewsUtil;
import com.cool.todayheadline.utils.UIHelper;

public class SettingsFragment extends Fragment
{
    private RelativeLayout relativeLayout;
    private Button newsCollector;
    private Button clearCache;
    private TextView textView;
    private Button exit_btn;
    private Button about_btn;
    private Button per_settings;
    private ImageView userPortrait;



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


        per_settings = view.findViewById(R.id.bt_callus);
        userPortrait = view.findViewById(R.id.iv_userphoto);

        per_settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent a = new Intent(getActivity(), PreferenceActivity.class);
                Bundle bundle = new Bundle();
                a.putExtras(bundle);
                startActivity(a);
            }
        });


        about_btn = view.findViewById(R.id.bt_resetlogin);
        about_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent a = new Intent(getActivity(), InfoActivity.class);
                startActivity(a);
            }
        });
        exit_btn = view.findViewById(R.id.bt_exit);
        textView = (TextView) view.findViewById(R.id.tv_username);

        textView.setText(Const.USER_NAME);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.re_myinfo);
        relativeLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if (Const.USER_ID == 0)
                {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        if (Const.USER_ID != 0)
        {
            Resources resources = getActivity().getResources();
            Drawable img = resources.getDrawable(R.mipmap.userportrait);
            userPortrait.setImageDrawable(img);

            exit_btn.setVisibility(View.VISIBLE);
        }

        exit_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Const.USER_ID = 0;
                Resources resources = getActivity().getResources();
                Drawable img = resources.getDrawable(R.mipmap.userportrait_default);
                userPortrait.setImageDrawable(img);
                Toast.makeText(getActivity(), "??????????????????", Toast.LENGTH_LONG).show();
                textView.setText("?????????");
                exit_btn.setVisibility(View.INVISIBLE);
            }
        });
        newsCollector = view.findViewById(R.id.bt_opinion);
        newsCollector.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), NewsCollectorActivity.class);
                startActivity(intent);
            }
        });

        clearCache = view.findViewById(R.id.bt_aboutus);
        clearCache.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!new UIHelper().isShowing())
                {
                    new UIHelper().showDialogForLoading(getActivity(), "????????????", true);
                }
                PreferenceNewsUtil.deleteUserAllNews(Const.USER_ID + "");
                PreferenceNewsUtil.cache_deleteAllNews();
                ImgCacheUtil.deleteCache();

                new UIHelper().hideDialogForLoading();

                Toast.makeText(getActivity(), "????????????", Toast.LENGTH_SHORT).show();
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
