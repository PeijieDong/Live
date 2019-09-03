package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.mymusic.music.R;
import com.mymusic.music.base.BaseActivity;

public class SignInActivity extends BaseActivity {


    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sign_in);
    }

    @Override
    protected void LoadData() {
//        initNet();
    }

//    private void initNet() {
//        NetRequest.postFormRequest(UrlManager.SIGNIN, null, new NetRequest.DataCallBack() {
//            @Override
//            public void requestSuccess(String result) throws Exception {
//
//            }
//
//            @Override
//            public void requestFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void TokenFail() {
//
//            }
//        });
//    }
}
