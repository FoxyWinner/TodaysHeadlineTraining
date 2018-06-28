package com.cool.todayheadline.utils;


public class Info {

//    String url="http://v.juhe.cn/toutiao/index?type="+userPreference+"&key=5465c4c5d60f72c3d756a9f1a9b8437d";
    private static String domain = "http://v.juhe.cn/toutiao/index";//亿利集团更换的新服务器
    public static String path_queryNewsItems(String type)
    {
        String path;
        if(!"".equals(type) || type!=null)
        {
            path = domain + "?type=" + type + "&key=5465c4c5d60f72c3d756a9f1a9b8437d";
        }
        else
        {
            path = "http://" + domain + "?type=&key=5465c4c5d60f72c3d756a9f1a9b8437d";
        }
        return path;
    }
}
