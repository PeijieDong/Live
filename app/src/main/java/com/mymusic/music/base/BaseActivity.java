package com.mymusic.music.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Create By mr.mao in 2019/5/28 19:35
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(null != getIntent()){
            initVariables(getIntent());
        }else{
            initVariables(null);
        }
        ActivityCollector.addActivity(this);
        initViews(savedInstanceState);
        LoadData();
    }

    protected abstract void initVariables(Intent intent);

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void LoadData();

    public BaseActivity getActivity(){
        return this;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
