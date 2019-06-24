package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.mymusic.music.R;
import com.mymusic.music.base.BaseActivity;

import butterknife.BindView;

public class WebActivity extends BaseActivity {

    @BindView(R.id.web)
    WebView webView;
    String url;
    @Override
    protected void initVariables(Intent intent) {
        url = intent.getStringExtra("url");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_web);
    }

    @Override
    protected void LoadData() {
        webView.loadUrl(url);
    }
}
