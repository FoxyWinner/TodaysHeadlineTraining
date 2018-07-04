package com.cool.todayheadline.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cool.todayheadline.R;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.utils.DownloadTask;
import com.cool.todayheadline.utils.Info;

public class FavoriteFragment extends Fragment
{
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private SwipeRefreshLayout refreshLayout;

    public FavoriteFragment()
    {
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_newsitem_list, container, false);

        sharedPreferences = getActivity().getSharedPreferences(Const.SP_USER_PREFEREBCE, Context.MODE_PRIVATE);

        String userPreference = sharedPreferences.getString(Const.USER_PREFERENCE, "Null");

        Context context = view.getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.list_refresh);
        refreshLayout.setEnabled(false);
        refreshLayout.setColorSchemeColors(R.color.colorPrimary);



        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        String url = Info.path_queryNewsItems(userPreference);
        String[] urls = {url};
        new DownloadTask(refreshLayout,recyclerView, getActivity()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,urls);
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
