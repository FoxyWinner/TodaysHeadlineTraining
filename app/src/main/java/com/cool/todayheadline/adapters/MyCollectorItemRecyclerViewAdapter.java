package com.cool.todayheadline.adapters;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cool.todayheadline.R;
import com.cool.todayheadline.activities.NewsDetailActivity;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.utils.DownloadImageTask;
import com.cool.todayheadline.utils.PreferenceNewsUtil;
import com.cool.todayheadline.vo.NewsItem;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

public class MyCollectorItemRecyclerViewAdapter extends RecyclerView.Adapter<MyCollectorItemRecyclerViewAdapter.ViewHolder>
{
    private static String PARAM_URL = "NEWS_DETAIL_URL";

    private RecyclerView recyclerView;
    private final List<NewsItem> mValues;
    private static final String TAG = "MyNewsItemRecyclerViewA";
    private final Context mActivityContext;

    public MyCollectorItemRecyclerViewAdapter(Context context, RecyclerView recyclerView, List<NewsItem> newsItemList)
    {
        mActivityContext = context;
        this.recyclerView = recyclerView;
        mValues = newsItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_news_collector_listitem, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {

        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mAuthorDateView.setText(mValues.get(position).getAuthor()+"\n"+mValues.get(position).getDate());

        //????????????????????????viewholder??????????????????listview??????????????????????????????????????????????????????????????????????????????

        //????????????item?????????????????????????????????
        Resources resources = mActivityContext.getResources();
        Drawable loadingImg = resources.getDrawable(R.mipmap.img_loading);
        holder.mImageView.setImageDrawable(loadingImg);

        new DownloadImageTask(holder.mImageView,mActivityContext).execute(mValues.get(position).getPic_url());


        //?????????item??????????????????

        holder.mLinearLayout.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent();
                Bundle bundle=new Bundle();
                intent.setClass(mActivityContext, NewsDetailActivity.class);
                bundle.putString(PARAM_URL, holder.mItem.getUrl());
                intent.putExtras(bundle);
                mActivityContext.startActivity(intent);
            }
        });

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PreferenceNewsUtil.deleteNews(Const.USER_ID+"",mValues.get(position).getId());
                mValues.remove(position);
                MyCollectorItemRecyclerViewAdapter.this.notifyItemRemoved(position);
                MyCollectorItemRecyclerViewAdapter.this.notifyItemRangeChanged(0,MyCollectorItemRecyclerViewAdapter.this.mValues.size());

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
        public final LinearLayout mLinearLayout;
        public final ImageView mImageView;
        public final Button mDeleteButton;
        public final TextView mTitleView;
        public final TextView mAuthorDateView;

        public NewsItem mItem;

        public ViewHolder(View view)
        {
            super(view);
            mView = (SwipeMenuLayout) view;
            mLinearLayout = (LinearLayout)view.findViewById(R.id.news_item_ll);
            mTitleView = (TextView) view.findViewById(R.id.news_item_title_tv);
            mAuthorDateView = (TextView) view.findViewById(R.id.news_item_author_date_tv);
            mImageView = (ImageView) view.findViewById(R.id.news_item_iv);
            mDeleteButton = (Button) view.findViewById(R.id.news_item_delete_collect_bt);
        }
    }
}
