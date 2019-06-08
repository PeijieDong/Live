package com.mymusic.music;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;

import com.mymusic.music.Util.LocaleUtils;

import java.util.Locale;

/**
 * Create By mr.mao in 2019/6/7 20:39
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class Live extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        Locale _UserLocale=LocaleUtils.getUserLocale(this);
//        LocaleUtils.updateLocale(this, _UserLocale);
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
}
