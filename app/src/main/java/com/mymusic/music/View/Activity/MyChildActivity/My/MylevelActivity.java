package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mymusic.music.DataBean.LevelDate;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;

import okhttp3.Request;

public class MylevelActivity extends BaseActivity {

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mylevel);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        NetRequest.postFormHeadRequest(UrlManager.Level, null, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                LevelDate bean = GsonUtil.GsonToBean(result, LevelDate.class);

            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }
}
