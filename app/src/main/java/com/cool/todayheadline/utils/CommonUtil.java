package com.cool.todayheadline.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class CommonUtil
{
    public static InputStream convertBitmapToIS(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        InputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        return isBm;
    }
    public static void addMiddleTitle(Context context, CharSequence title, Toolbar toolbar) {

        TextView textView = new TextView(context);
        textView.setText(title);

        Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        toolbar.addView(textView, params);

    }

    public static String SpellToHanzi(String spell)
    {
        String hanzi="";
        switch (spell)
        {
            case "":
            {
                hanzi="头条";
                break;
            }
            case "shehui":
            {
                hanzi="社会";
                break;
            }
            case "guonei":
            {
                hanzi="国内";
                break;
            }
            case "guoji":
            {
                hanzi="国际";
                break;
            }
            case "yule":
            {
                hanzi="娱乐";
                break;
            }
            case "tiyu":
            {
                hanzi="体育";
                break;
            }
            case "junshi":
            {
                hanzi="军事";
                break;
            }
            case "keji":
            {
                hanzi="科技";
                break;
            }
            case "caijing":
            {
                hanzi="财经";
                break;
            }
            case "shishang":
            {
                hanzi="时尚";
                break;
            }
        }
        return hanzi;
    }
}
