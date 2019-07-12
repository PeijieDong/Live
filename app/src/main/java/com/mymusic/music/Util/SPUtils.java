package com.mymusic.music.Util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.mymusic.music.DataBean.AdvEntity;

/**
 * @author legendary
 * @version 1.0
 * @created 2018/6/12 2:14
 * @description 对token进行增删改查
 */
public class SPUtils {


    /**
     * 保存token必更新内存token
     * @param token
     * @param context
     */
    public static void saveToken(String token, Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("token", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("token", token);
        editor.commit();
        HttpManager.getInstance().setToken(token);
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("token", Activity.MODE_PRIVATE);
        return sharedPreferences.getString("token",null);
    }

    /**
     * 删除token必更新内存token="access_token"
     * @param context
     */
    public static void delToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("token", Activity.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
        HttpManager.getInstance().setToken("access_token");
    }

    /**
     *校验本地是否有token
     * @param context
     */
    public static boolean isEmptyToken(Context context) {
        String token=SPUtils.getToken(context);
        return TextUtils.isEmpty(token);
    }

    public static void saveUserAndPwd(String userName, String userPwd, Context context){
        SharedPreferences mySharedPreferences = context.getSharedPreferences("userPassword", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("userName", userName);
        editor.putString ("userPwd", userPwd);
        editor.commit();
    }
    public static String getUserName(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("userPassword", Activity.MODE_PRIVATE);
        return sharedPreferences.getString ("userName","");
    }
    public static String getUserPwd(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("userPassword", Activity.MODE_PRIVATE);
        return sharedPreferences.getString ("userPwd","");
    }

    // 删除用户信息
    public  static void delUserInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Activity.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }


    /**
     * 存储启动广告信息
     * @param adv
     * @param context
     */
    public static void saveAdvInfo(AdvEntity adv, Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("avd", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("link", adv.list.link);
        editor.putString("image", adv.list.imgurl);
        editor.commit();
    }


//   id	string	广告id
//   name	string	广告名称
//   link	string	广告链接
//   image	string	广告图片

    /**
     * 获取启动广告信息
     * @param context
     * @return
     */
    public static AdvEntity getAdvInfo(Context context) {
        AdvEntity avd=new AdvEntity();
        SharedPreferences sharedPreferences = context.getSharedPreferences("avd", Activity.MODE_PRIVATE);
        avd.list.link = sharedPreferences.getString("link","");
        avd.list.imgurl = sharedPreferences.getString("image", "");
        return avd;
    }
    /**
     * 删除启动广告信息
      */
    public  static void delAdvInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("avd", Activity.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }


    /**
     * 存储启动广告本地缓存path
     * @param path
     * @param context
     */
    public static void saveAdvLocPath(String path, Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("avd_loc_path", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("path", path);
        editor.commit();
    }
    /**
     * 获取启动广告本地缓存path
     * @param context
     * @return
     */
    public static String getAdvLocPath(Context context) {
        AdvEntity avd=new AdvEntity();
        SharedPreferences sharedPreferences = context.getSharedPreferences("avd_loc_path", Activity.MODE_PRIVATE);
        return  sharedPreferences.getString("path", "");
    }
    /**
     * 删除启动广告本地缓存path
     */
    public  static void delAdvLocPath(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("avd_loc_path", Activity.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }


    /**
     *校验本地图片是否有缓存path
     * @param context
     */
    public static boolean isEmptyAdvLocPath(Context context) {
        String path=SPUtils.getAdvLocPath(context);
        return TextUtils.isEmpty(path);
    }
}
