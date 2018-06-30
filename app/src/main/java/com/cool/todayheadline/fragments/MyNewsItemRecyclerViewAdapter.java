package com.cool.todayheadline.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cool.todayheadline.R;
import com.cool.todayheadline.activities.NewsDetailActivity;
import com.cool.todayheadline.bean.NewsItem_table;
import com.cool.todayheadline.fragments.HomeFragment.OnListFragmentInteractionListener;
import com.cool.todayheadline.utils.AssemblerUtil;
import com.cool.todayheadline.utils.DownloadImageTask;
import com.cool.todayheadline.utils.PreferenceNewsUtil;
import com.cool.todayheadline.utils.TSnackBarUtil;
import com.cool.todayheadline.vo.NewsItem;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

public class MyNewsItemRecyclerViewAdapter extends RecyclerView.Adapter<MyNewsItemRecyclerViewAdapter.ViewHolder>
{
    private static String PARAM_URL = "NEWS_DETAIL_URL";

    private RecyclerView recyclerView;
    private final List<NewsItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private static final String TAG = "MyNewsItemRecyclerViewA";
    private final Context mActivityContext;

    public MyNewsItemRecyclerViewAdapter(Context context,RecyclerView recyclerView,List<NewsItem> newsItemList, OnListFragmentInteractionListener listener)
    {
        mActivityContext = context;
        this.recyclerView = recyclerView;
        mValues = newsItemList;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_newsitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mAuthorDateView.setText(mValues.get(position).getAuthor()+"\n"+mValues.get(position).getDate());

        //解决因为item重用带来的图片显示错位
        Resources resources = mActivityContext.getResources();
        Drawable loadingImg = resources.getDrawable(R.mipmap.img_loading);
        holder.mImageView.setImageDrawable(loadingImg);

        //图片加载
        new DownloadImageTask(holder.mImageView).execute(mValues.get(position).getPic_url());


        holder.mCancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mValues.remove(position);
                MyNewsItemRecyclerViewAdapter.this.notifyItemRemoved(position);
                MyNewsItemRecyclerViewAdapter.this.notifyItemRangeChanged(0,MyNewsItemRecyclerViewAdapter.this.mValues.size());
            }
        });

        holder.mCollectButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                NewsItem_table newsItem_table = AssemblerUtil.transformToPOJO(holder.mItem);
                PreferenceNewsUtil.insertNews(newsItem_table);
                holder.mView.quickClose();

                TSnackBarUtil.showTBar(recyclerView,"收藏成功");

            }
        });


        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                Bundle bundle=new Bundle();
                intent.setClass(mActivityContext, NewsDetailActivity.class);
                bundle.putString(PARAM_URL, holder.mItem.getUrl());

                intent.putExtras(bundle);
                mActivityContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final SwipeMenuLayout mView;
        public final ImageView mImageView;
        public final Button mCancelButton;
        public final Button mCollectButton;


        public final TextView mTitleView;
        public final TextView mAuthorDateView;

        public NewsItem mItem;

        public ViewHolder(View view)
        {
            super(view);
            mView = (SwipeMenuLayout) view;
            mTitleView = (TextView) view.findViewById(R.id.news_item_title_tv);
            mAuthorDateView = (TextView) view.findViewById(R.id.news_item_author_date_tv);
            mImageView = (ImageView) view.findViewById(R.id.news_item_iv);
            mImageView.setTag(false);//默认设置mImageView没有加载
            mCancelButton = (Button) view.findViewById(R.id.news_item_cancel_bt);
            mCollectButton = (Button) view.findViewById(R.id.news_item_collect_bt);
        }

        @Override
        public String toString()
        {
            return "ViewHolder{" +
                    "mView=" + mView +
                    ", mImageView=" + mImageView +
                    ", mTitleView=" + mTitleView +
                    ", mAuthorDateView=" + mAuthorDateView +
                    ", mItem=" + mItem +
                    '}';
        }
    }
}
