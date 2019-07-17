package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mymusic.music.R;
import com.mymusic.music.Util.AppUtil;
import com.mymusic.music.base.BaseActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;

public class MyaboutActivity extends BaseActivity {

    @BindView(R.id.app_name)
    TextView appName;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_myabout);
    }

    @Override
    protected void LoadData() {
        appName.setText(AppUtil.getAppName(this));
    }

    @OnClick({R.id.back})
    public void ClickEvent(){
        finish();
    }
}
