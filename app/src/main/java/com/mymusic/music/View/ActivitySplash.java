package com.mymusic.music.View;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.mymusic.music.ActivityAdv;
import com.mymusic.music.DataBean.AdvEntity;
import com.mymusic.music.MainActivity;
import com.mymusic.music.R;
import com.mymusic.music.Util.GlideUtils;
import com.mymusic.music.Util.HttpManager;
import com.mymusic.music.Util.ListenerManger;
import com.mymusic.music.Util.NetEngine;
import com.mymusic.music.Util.RequestFactory;
import com.mymusic.music.Util.SPUtils;
import com.mymusic.music.base.BaseActivity;

import java.io.File;

public class ActivitySplash extends BaseActivity implements ListenerManger.ImageDownLoadCallBack{



    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void LoadData() {
        NetEngine.getInstance().getPublicEngine().advGetRequest(handler);
    }

    private AdvEntity advEntity;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //dismissLoadingDialog();
            if (msg.arg1 == RequestFactory.REQUEST_OK) {
                switch (msg.arg2) {
                    case RequestFactory.PUBLIC_ADV:
                        advEntity = (AdvEntity) msg.obj;
                        if (HttpManager.getInstance().isSuccessFul(advEntity)&&!TextUtils.isEmpty(advEntity.data.list.imgurl)) {
                            GlideUtils.downLoadImg(ActivitySplash.this, advEntity.data.list.imgurl, ActivitySplash.this);
                        } else {
                            isGotoAdvActivity(!TextUtils.isEmpty(advEntity.data.list.imgurl));
                            //ToastUtils.showToast(ActivitySplash.this, advEntity.msg);
                        }
                        break;
                    case RequestFactory.PUBLIC_ADV_DOWNLOAD_SUCCESS:
                        //存储广告信息
                        SPUtils.saveAdvInfo(advEntity.data,ActivitySplash.this);
                        //更新广告图片本地存储path
                        String  path = (String) msg.obj;
                        SPUtils.saveAdvLocPath(path,ActivitySplash.this);
                        //跳转广告页
                        isGotoAdvActivity();
                        break;
                    case RequestFactory.PUBLIC_ADV_DOWNLOAD_FAILED:
                        isGotoAdvActivity();
                        break;
                }
            } else {
                isGotoAdvActivity();
                //ToastUtils.showToast(ActivitySplash.this, R.string.hint_net_err);
            }
        }
    };


    public void  isGotoAdvActivity(){
        isGotoAdvActivity(true);
    }
    public void  isGotoAdvActivity(boolean isGotoAdv){
        //本地广告信息如果有就跳转
        if((!SPUtils.isEmptyAdvLocPath(ActivitySplash.this))&&isGotoAdv) {
            //去广告页
            startActivity(new Intent(this,ActivityAdv.class));
            overridePendingTransition(0, 0);
        }else {
            //去主页
            if(!isGotoAdv){
                SPUtils.delAdvLocPath(this);
            }
            startActivity(new Intent(this,MainActivity.class));
        }
        finish();
    }



    @Override
    public void onDownLoadSuccess(String url, final File file) {
        Message msg=new Message();
        msg.arg2=RequestFactory.PUBLIC_ADV_DOWNLOAD_SUCCESS;
        msg.arg1 = RequestFactory.REQUEST_OK;
        msg.obj=file.getPath();
        handler.sendMessage(msg);
    }

    @Override
    public void onDownLoadFailed() {
        Message msg=new Message();
        msg.arg2=RequestFactory.PUBLIC_ADV_DOWNLOAD_FAILED;
        msg.arg1 = RequestFactory.REQUEST_OK;
        handler.sendMessage(msg);
    }

    @Override
    protected void onDestroy() {
        HttpManager.getInstance().cancleRequestCall(RequestFactory.PUBLIC_ADV);
        super.onDestroy();
    }
}
