package com.mymusic.music.Util;

import android.os.Handler;
import com.mymusic.music.DataBean.AdvEntity;


/**
 * Created by tly on 2017/7/26.
 */
public class PublicEngine {

    /**
     * 获取启动广告图
     */
    public void advGetRequest(Handler handler) {
        try {
            HttpManager.getInstance().callRequest(true, RequestFactory.PUBLIC_ADV, UrlConstants.PUBLIC_ADV_URL, null, HttpManager.GET, AdvEntity.class, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
