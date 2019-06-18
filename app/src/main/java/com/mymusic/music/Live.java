package com.mymusic.music;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;
import com.mymusic.music.DataBean.User;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.MyFileNameGenerator;
import com.mymusic.music.Util.SharedPrefrenceUtils;

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
        String user = SharedPrefrenceUtils.getString(context, "user", null);
        User bean = GsonUtil.GsonToBean(user, User.class);
        return bean;
    }

    public String getToken(Context context){
        String user = SharedPrefrenceUtils.getString(context, "user", null);
        User bean = GsonUtil.GsonToBean(user, User.class);

        return bean.getList().getToken();
    }

    public void put(Context context,String s){
        SharedPrefrenceUtils.getString(context,"user",s);
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
}
