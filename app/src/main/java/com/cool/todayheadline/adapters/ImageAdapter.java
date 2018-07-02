package com.cool.todayheadline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cool.todayheadline.R;
import com.cool.todayheadline.bean.Grid_bean;

public class ImageAdapter extends BaseAdapter {



    private Context context;


    public ImageAdapter(Context context){


        this.context = context;

    }
    private Integer[] mPics  = {R.mipmap.grid_shehui_pic,R.mipmap.grid_guonei_pic,R.mipmap.grid_guoji_pic,
            R.mipmap.grid_yule_pic, R.mipmap.grid_tiyu_pic, R.mipmap.grid_junshi_pic, R.mipmap.grid_keji_pic,
            R.mipmap.grid_caijing_pic, R.mipmap.grid_shishang_pic};

    private Integer[] pNames = {R.string.p_name1, R.string.p_name2,R.string.p_name3, R.string.p_name4,
            R.string.p_name5, R.string.p_name6, R.string.p_name7, R.string.p_name8, R.string.p_name9,};


    @Override
    public int getCount(){
        return mPics.length;
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    //get the current selector's id number
    @Override
    public long getItemId(int position) {
        return position;
    }

    //create view method
    @Override
    public View getView(int position, View view, ViewGroup viewgroup) {
        Grid_bean gView;
        if(view==null) {
            gView = new Grid_bean();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.grid_item, null);
            view.setTag(gView);
            view.setPadding(15, 15, 15, 15);  //每格的间距
        } else {
            gView = (Grid_bean) view.getTag();
        }

        gView.imageView = (ImageView)view.findViewById(R.id.gridImage);
        gView.imageView.setBackgroundResource(mPics[position]);
        gView.textView = (TextView)view.findViewById(R.id.gridText);
        gView.textView.setText(pNames[position]);

        return view;
    }
}


