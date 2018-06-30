package com.cool.todayheadline.utils;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.cool.todayheadline.R;

public class TSnackBarUtil
{
    public static void showTBar(View view,String text)
    {
        TSnackbar snackbar = TSnackbar.make(view, text, TSnackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.WHITE);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor("#464646"));
        snackbar.setIconLeft(R.drawable.ic_favorite_black_24dp, 24);
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
//        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextColor(Color.WHITE);

        snackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        snackbar.show();
    }
}
