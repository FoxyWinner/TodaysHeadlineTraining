package com.cool.todayheadline.bean;

/**
 * Created by 柳宾辉 on 2018/6/27.
 */

public class Result {
    private String stat;
    private Data data[];

    public Data[] getData() {
        return data;
    }

    public String getStat() {
        return stat;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
