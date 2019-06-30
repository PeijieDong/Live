package com.mymusic.music;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.danikula.videocache.HttpProxyCacheServer;
import com.mymusic.music.DataBean.User;
import com.mymusic.music.DataBean.UserBean;
import com.mymusic.music.DataBean.UserInfo;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.MyFileNameGenerator;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.View.Activity.Login.LoginActivity;

import org.litepal.LitePal;

/**
 * Create By mr.mao in 2019/6/7 20:39
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Live extends Application {
    private static Live live;
    private HttpProxyCacheServer proxy;

    public static Live getInstance() {
        if(live != null){
            return live;
        }
        return new Live();
    }

    public User get(Context context){
        if(SharedPrefrenceUtils.getString(context, "user", null) == null){
            return null;
        }else{
            String user = SharedPrefrenceUtils.getString(context, "user", null);
            User bean = GsonUtil.GsonToBean(user, User.class);
            return bean;
        }
    }

    public String getToken(Context context){
        if(getUser(context) != null){
            UserBean user = getUser(context);
            return user.getData().getToken();
        }else{
            return null;
        }
    }

    public void put(Context context,String s){
        SharedPrefrenceUtils.saveString(context,"user",s);
    }

    public void clear(Context context){
        SharedPrefrenceUtils.clearn(context,"user");
        SharedPrefrenceUtils.clearn(context,"userinfo");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
//        Locale _UserLocale=LocaleUtils.getUserLocale(this);
//        LocaleUtils.updateLocale(this, _UserLocale);
    }
    public static HttpProxyCacheServer getProxy(Context context) {
        Live app = (Live) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        Locale _UserLocale=LocaleUtils.getUserLocale(this);
//        //系统语言改变了应用保持之前设置的语言
//        if (_UserLocale != null) {
//            Locale.setDefault(_UserLocale);
//            Configuration _Configuration = new Configuration(newConfig);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                _Configuration.setLocale(_UserLocale);
//            } else {
//                _Configuration.locale =_UserLocale;
//            }
//            getResources().updateConfiguration(_Configuration, getResources().getDisplayMetrics());
//        }
//    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(1024 * 1024 * 1024)       // 1 Gb for cache
                .fileNameGenerator(new MyFileNameGenerator())
                .build();
    }

    public void putUser(Context context, String result) {
        SharedPrefrenceUtils.saveString(context,"userinfo",result);
    }
    public UserBean getUser(Context context){
        if(SharedPrefrenceUtils.getString(context, "userinfo", null) == null){
            return null;
        }else{
            String user = SharedPrefrenceUtils.getString(context, "userinfo", null);
            UserBean bean = GsonUtil.GsonToBean(user, UserBean.class);
            return bean;
        }
    }
}
