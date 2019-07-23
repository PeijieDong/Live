package com.mymusic.music.Util;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.ViewUtils;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.mymusic.music.R;

/**
 * Create By mr.mao in 2019/7/22 20:42
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class ToastUtil {

    public static void show(Context context, String msg, int duration){
        showToast(context,msg,1500);
    }

    static Toast toast;
    static TextView contentView;

    /**
     * 自定义Toast的样式与位置
     *
     * @param context
     * @param message
     * @param duration
     */
    private static void showToast(Context context, String message, int duration) {
        if (context == null) {
            return ;
        }
        try {
            if (toast == null || contentView == null) {
                int dp30 = DpPxUtils.dip2px(context, 20);
                int dp20 = DpPxUtils.dip2px(context, 7);
                contentView = new TextView(context);
                contentView.setGravity(Gravity.CENTER);
                contentView.setBackgroundResource(R.drawable.toast_background);
                contentView.setTextSize(14);
                contentView.setTextColor(context.getResources().getColor(R.color.white));
                contentView.setPadding(dp30, dp20, dp30, dp20);
                toast = new Toast(context);
                toast.setGravity(Gravity.BOTTOM, 0, 180);
                toast.setView(contentView);
                toast.setDuration(duration);
            }
            contentView.setText(message);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
