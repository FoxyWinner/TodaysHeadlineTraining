package com.cool.todayheadline.utils;

public class Const
{
    public static final String USER_PREFERENCE = "preference";
    public static String USER_NAME="请登录";

    //该参数默认为0，即本地用户，登陆后该参数会改变为UserId
    public static int USER_ID = 0;


    public static String SP_USER_PREFEREBCE = "SP_USER_PREFEREBCE";
    public static String PARAM_VO = "NEWS_DETAIL_VO";
    public static String NEWS_ITEM_LIST = "NEWS_ITEM_LIST";


    //直接存URL
    public static String REAL_URL="";


    public static  int EMAIL = 1;

    //DiskLruCache缓冲池大小
    public static long CACHE_MAX_SIZE =  10 * 1024 * 1024;//10MB;

    //已经跳转，提示异步任务可以结束了
    public static boolean HAS_JUMP = false;





}
