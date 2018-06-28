package com.cool.todayheadline.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cool.todayheadline.R;
import com.cool.todayheadline.fragments.dummy.DummyNewsItems;
import com.cool.todayheadline.utils.DownloadTask;
import com.cool.todayheadline.utils.UIHelper;
import com.cool.todayheadline.vo.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class HomeFragment extends Fragment
{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HomeFragment()
    {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HomeFragment newInstance(int columnCount)
    {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
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
        String per = getActivity().getIntent().getStringExtra("per");

        // Set the adapter
        if (view instanceof RecyclerView)
        {
            Context context = view.getContext();

            RecyclerView recyclerView = (RecyclerView) view;
            //设置分割线
            recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            String url="http://v.juhe.cn/toutiao/index?type="+per+"&key=5465c4c5d60f72c3d756a9f1a9b8437d";
            String[] urls={url};
            List<NewsItem> newsItemList=new ArrayList<NewsItem>();
            new DownloadTask(recyclerView,mListener,getActivity()).execute(urls);
            //new UIHelper().hideDialogForLoading();
            //recyclerView.setAdapter(new MyNewsItemRecyclerViewAdapter(getActivity(),newsItemList, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener)
//        {
//            mListener = (OnListFragmentInteractionListener) context;
//        }
//        else
//        {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
//        mListener = null;
    }

    public interface OnListFragmentInteractionListener
    {
        void onListFragmentInteraction(NewsItem item);
    }
}
