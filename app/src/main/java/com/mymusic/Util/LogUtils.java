package com.mymusic.Util;

import android.util.Log;

/**
 *Create By mr.mao in 2019/4/11
 *QQ 583723781 情之初，莫相忘
 *一叶扁舟轻帆卷，暂泊楚江南岸。
**/

public class LogUtils {
    // public static boolean isShow = false; // false 表示上线模式
    public static boolean isShow = true;  // true 表示开发模式

    //对应级别为verbose
    public static void v(String tag, String msg) {
        if (isShow == true) {
            Log.v(tag, msg);
        }
    }

    //对应级别为debug
    public static void d(String tag, String msg) {
        if (isShow == true) {
            Log.d(tag, msg);
        }
    }

    //对应级别为info
    public static void i(String tag, String msg) {
        if (isShow == true) {
            Log.i(tag, msg);
        }
    }

    //对应级别为warn
    public static void w(String tag, String msg) {
        if (isShow == true) {
            Log.w(tag, msg);
        }
    }

    //对应级别为error
    public static void e(String tag, String msg) {
        if (isShow == true) {
            Log.e(tag, msg);
        }
    }
    public static void e(String tag, int msg) {
        if (isShow == true) {
            Log.e(tag, String.valueOf(msg));
        }
    }
}

