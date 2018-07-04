package com.cool.todayheadline.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cool.todayheadline.R;
import com.cool.todayheadline.adapters.MyNewsItemRecyclerViewAdapter;
import com.cool.todayheadline.utils.CommonUtil;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.utils.DownloadTask;
import com.cool.todayheadline.utils.Info;
import com.cool.todayheadline.vo.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class NewsItemsFragment extends Fragment
{

    private RecyclerView recyclerView;
    private static final String TAG = "NewsItemsFragment";
    private SwipeRefreshLayout refreshLayout;
    private String userPreference;

    //存放欢迎页或者PreferenceActivity传来的数据

    private List<NewsItem> newsItemList = new ArrayList<>();

    public NewsItemsFragment()
    {
    }

    public static NewsItemsFragment newInstance(String userPreference)
    {
        NewsItemsFragment fragment = new NewsItemsFragment();
        Bundle args = new Bundle();
        args.putString(Const.USER_PREFERENCE, userPreference);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            userPreference = getArguments().getString(Const.USER_PREFERENCE);
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_newsitem_list, container, false);

        Context context = view.getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.list_refresh);
        refreshLayout.setEnabled(false);
        refreshLayout.setColorSchemeColors(R.color.colorPrimary);



        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //判断预加载数据是否成功，若成功，直接使用预加载数据，不成功则DownloadTask

        newsItemList = (List<NewsItem>) getActivity().getIntent().getSerializableExtra(Const.NEWS_ITEM_LIST);
        if(newsItemList != null && newsItemList.size()>0 && "".equals(userPreference))
        {
            //直接把预加载数据填充进去
            Log.d(TAG, "onCreateView: 使用了预加载数据");
            recyclerView.setAdapter(new MyNewsItemRecyclerViewAdapter(getActivity(),recyclerView,newsItemList));
        }
        else
        {
            String url= Info.path_queryNewsItems(userPreference);
            Log.d(TAG, "onCreateView: "+userPreference);
            new DownloadTask(refreshLayout,recyclerView,getActivity(), CommonUtil.SpellToHanzi(userPreference)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url);
        }


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
