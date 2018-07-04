package com.cool.todayheadline.utils;


import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cool.todayheadline.R;
public class UIHelper
{
	
	/** 加载数据对话框 */
	private static Dialog mLoadingDialog;
	//单例模式

	/**
	 * @param context
	 * @param msg
	 * @param cancelable
	 */
	public static void showDialogForLoading(Activity context, String msg, boolean cancelable)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.layout_loading, null);
		TextView loadingText = (TextView)view.findViewById(R.id.id_tv_loading_dialog_text);
		loadingText.setText(" " +msg);
		mLoadingDialog = new Dialog(context, R.style.loading_dialog_style);
		mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		mLoadingDialog.setCanceledOnTouchOutside(false);
		mLoadingDialog.show();
	}
	
	/**
	 * 关闭加载对话框
	 */
	public static void hideDialogForLoading() {
		if(mLoadingDialog != null && mLoadingDialog.isShowing()) {
			mLoadingDialog.cancel();
		}
	}

	public static boolean isShowing()
	{
		//mLoadingDialog若=null直接返回false了
		if(mLoadingDialog == null)
			return false;
		else
		{
			if (mLoadingDialog.isShowing())
			{
				return true;
			}
			return false;
		}
	}
}
