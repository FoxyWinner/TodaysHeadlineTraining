package com.cool.todayheadline.bean;

/**
 * Created by 柳宾辉 on 2018/6/27.
 */

public class Sys {
    private String reason;
    private Result result;
    private String error_code;

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public String getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }

}
