package com.mymusic.music;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.AdvEntity;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.WebActivity;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;

import okhttp3.Request;

public class ActivityAdv extends BaseActivity implements View.OnClickListener{

    private AdvEntity bean;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_adv);
    }

    @Override
    protected void LoadData() {
        iv_adv = findViewById(R.id.iv_adv);
        bt_jump = findViewById(R.id.bt_jump);
        iv_adv.setOnClickListener(this);
        bt_jump.setOnClickListener(this);
        if(runnable==null){
            runnable=new Runnable() {
                @Override
                public void run() {
                    onClick(bt_jump);
                }
            };
        }
        handler.postDelayed(runnable , 5000);
        initNet();

    }

    private void initNet() {
        loading();
        NetRequest.postFormRequest(UrlManager.ADV, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                bean = GsonUtil.GsonToBean(result, AdvEntity.class);
                Glide.with(ActivityAdv.this).load(bean.getData().getList().getImgurl()).into(iv_adv);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
        hideloading();
    }


    private Runnable runnable=null;
    private Handler handler=new Handler(){};
    private ImageView iv_adv;
    private Button bt_jump;




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_adv:
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(bean.getData().getList().getLink());
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.bt_jump:
                startActivity(new Intent(this,MainActivity.class));
                handler.removeCallbacks(runnable);
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        try {
            handler.removeCallbacks(runnable);
        }catch (Exception e){
        }
        super.onDestroy();

    }
}
