package com.cool.todayheadline.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PackageInfoUtil
{
    public static int getVersionCode(Context context)
    {
        PackageManager packageManager = context.getPackageManager();

        // 获取包名
        String packageName = context.getPackageName();

        int flag = 0;
        PackageInfo packageInfo = null;

        // 获取packageInfo
        try {
            packageInfo = packageManager.getPackageInfo(packageName, flag);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // 获取packageInfo中的版本信息等信息
        int versionCode = 0;
        if (packageInfo != null) {
            versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
        }
        return versionCode;
    }
}
