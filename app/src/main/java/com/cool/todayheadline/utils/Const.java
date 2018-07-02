package com.cool.todayheadline.utils;

public class Const
{
    public static final String USER_PREFERENCE = "preference";
    public static String USER_NAME="请登录";

    //该参数默认为0，即本地用户，登陆后该参数会改变为UserId
    public static int USER_ID = 0;


    public static String PARAM_URL = "NEWS_DETAIL_URL";
    public static String PARAM_VO = "NEWS_DETAIL_VO";


    public static  int EMAIL = 1;

    //DiskLruCache缓冲池大小
    public static long CACHE_MAX_SIZE =  10 * 1024 * 1024;//10MB;





}
