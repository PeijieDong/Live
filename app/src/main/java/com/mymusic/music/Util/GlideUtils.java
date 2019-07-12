package com.mymusic.music.Util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by qingning on 2016/12/5.
 */
public class GlideUtils {

    /**
     * 加载图片的载体，图片会跟随载体生命周期销毁，??????好不要使用Application，图片会??????直存??????
     *
     * @param t   Fragment ?????? FragmentActivity ?????? Activity ?????? Context
     * @param <T> Fragment ?????? FragmentActivity ?????? Activity ?????? Context
     * @return glide请求管理??????
     */
    
    public static <T> RequestManager with(T t) {
        if (t instanceof Fragment) {
            Fragment with = (Fragment) t;
            return Glide.with(with);
        } else if (t instanceof FragmentActivity) {
            FragmentActivity with = (FragmentActivity) t;
            return Glide.with(with);
        } else if (t instanceof Activity) {
            Activity with = (Activity) t;
            return Glide.with(with);
        } else if (t instanceof android.app.Fragment) {
            android.app.Fragment with = (android.app.Fragment) t;
            return Glide.with(with);
        } else if (t instanceof Context) {
            Context with = (Context) t;
            return Glide.with(with);
        }
        else {
        	System.out.println("图片会一直存??????");
            //return Glide.with(MumuApplication.application);
            return null;
        }
    }


    /**
     * 单线程列队执行
     */
    private static ExecutorService singleExecutor = null;

    /**
     * 执行单线程列队执行
     */
    public static void runOnQueue(Runnable runnable) {
        if (singleExecutor == null) {
            singleExecutor = Executors.newSingleThreadExecutor();
        }
        singleExecutor.submit(runnable);
    }

    /**
     * 下载网络图片
     * @param imageView
     * @param imgUrl
     * @param context
     */
    /**
     * 启动图片下载线程
     */
    public static void downLoadImg(Context context, String url, ListenerManger.ImageDownLoadCallBack callBack) {
        DownLoadImageService service = new DownLoadImageService(context,url,callBack);
        //启动图片下载线程
        runOnQueue(service);
    }

}
