package com.cool.todayheadline.fragments;//package com.cool.todayheadline.fragments;
//
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
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.vo.NewsItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NewsListFragment extends Fragment
{

    // TODO: Customize parameter argument names
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsListFragment()
    {
    }


    public static NewsListFragment newInstance(String preference)
    {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putString(Const.USER_PREFERENCE, preference);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_newsitem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView)
        {
            Context context = view.getContext();

            RecyclerView recyclerView = (RecyclerView) view;
            //设置分割线
            recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            //判断是否有偏好，如果没有则加载全部条目，如果有，则剔除；
            if(getArguments()!=null)
            {
                //将items筛选


            }else
            {
                recyclerView.setAdapter(new MyNewsItemRecyclerViewAdapter(getActivity(),DummyNewsItems.ITEMS, (HomeFragment.OnListFragmentInteractionListener) mListener));

            }
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
