package com.cool.todayheadline.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cool.todayheadline.R;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.utils.DownloadTask;
import com.cool.todayheadline.utils.Info;

public class FavoriteFragment extends Fragment
{
    private HomeFragment.OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private SharedPreferences sp ;
    public FavoriteFragment()
    {
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) //没传过数据来
        {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_newsitem_list, container, false);
        String userPreference = getActivity().getIntent().getStringExtra(Const.USER_PREFERENCE);

        sp = getActivity().getSharedPreferences("SP", Context.MODE_PRIVATE);

        String value = sp.getString("Value","Null");




        Log.e("FavoriteFragment",value);


        // Set the adapter
            Context context = view.getContext();

            recyclerView = (RecyclerView) view.findViewById(R.id.list);
            //设置分割线
            recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            String url= Info.path_queryNewsItems(value);
            String[] urls={url};
            new DownloadTask(recyclerView,getActivity()).execute(urls);
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
