package com.mymusic.music;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.danikula.videocache.HttpProxyCacheServer;
import com.mymusic.music.DataBean.User;
import com.mymusic.music.DataBean.UserBean;
import com.mymusic.music.Util.ContextUtils;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.JZExoPlayer;
import com.mymusic.music.Util.MyFileNameGenerator;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.litepal.LitePal;

import cn.jpush.android.api.JPushInterface;
import cn.jzvd.JZMediaInterface;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZMediaSystem;
import cn.jzvd.JZVideoPlayer;

/**
 * Create By mr.mao in 2019/6/7 20:39
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Live extends Application {
    private static Live live;
    private HttpProxyCacheServer proxy;
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.navi_title_color,R.color.navi_title_color);//全局设置主题颜色
                return new WaterDropHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
    }

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
        if(get(context) != null){
            User user = get(context);
            return user.getList().getToken();
        }else{
            return "";
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
        ContextUtils.init(this);
        MultiDex.install(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JZVideoPlayer.setMediaInterface(new JZExoPlayer());  //exo
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
