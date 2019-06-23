package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.mymusic.music.R;
import com.mymusic.music.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MyshareActivity extends BaseActivity {

    @BindView(R.id.web)
    WebView web;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_myshare);
    }

    @Override
    protected void LoadData() {
        web.loadUrl("http://live.shuiqiao.net/users/share");
    }
}
