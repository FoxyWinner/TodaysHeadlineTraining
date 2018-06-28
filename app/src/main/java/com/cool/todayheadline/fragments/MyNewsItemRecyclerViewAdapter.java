package com.cool.todayheadline.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cool.todayheadline.R;
import com.cool.todayheadline.fragments.HomeFragment.OnListFragmentInteractionListener;
import com.cool.todayheadline.utils.DownloadImageTask;
import com.cool.todayheadline.vo.NewsItem;

import java.util.List;

public class MyNewsItemRecyclerViewAdapter extends RecyclerView.Adapter<MyNewsItemRecyclerViewAdapter.ViewHolder>
{

    private final List<NewsItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private static final String TAG = "MyNewsItemRecyclerViewA";

    public MyNewsItemRecyclerViewAdapter(List<NewsItem> newsItemList, OnListFragmentInteractionListener listener)
    {
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


        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (null != mListener)
                {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
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
        public final View mView;
        public final ImageView mImageView;
        public final ImageView mCancelButton;

        public final TextView mTitleView;
        public final TextView mAuthorDateView;
        public NewsItem mItem;

        public ViewHolder(View view)
        {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.news_item_title_tv);
            mAuthorDateView = (TextView) view.findViewById(R.id.news_item_author_date_tv);
            mImageView = (ImageView) view.findViewById(R.id.news_item_iv);
            mCancelButton = (ImageView) view.findViewById(R.id.news_item_cancel_bt);
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
