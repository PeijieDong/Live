package com.mymusic.music.Util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.mymusic.music.BuildConfig;

import org.litepal.util.LogUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;

/**
 * Create By mr.mao in 2019/7/10 23:15
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/


public class LoggerInterceptor implements Interceptor {

    private static String TAG = "LoggerInterceptor";
    private boolean isDebug;

    public LoggerInterceptor(boolean isDebug) {
        this(TAG, isDebug);
    }

    public LoggerInterceptor(String tag, boolean isDebug) {
        this.isDebug = isDebug;
        TAG = tag;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (BuildConfig.DEBUG || isDebug) {
            Log.d(TAG, String.format("发送请求:%s on %s%n%s%n%s",
                    request.url(), chain.connection(), request.headers(), request.body()));
        }
        return chain.proceed(request);
    }
}


