package com.mymusic.music.Util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Create By mr.mao in 2019/7/22 21:12
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class DpPxUtils {
    //dp转px
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    //px转dp
    public static int px2dip(Context context, int pxValue) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, context.getResources().getDisplayMetrics()));
    }


}
