package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mymusic.music.R;
import com.mymusic.music.View.Activity.MyChildActivity.LiveActivity.LiveGiftActivity;
import com.mymusic.music.View.Activity.MyChildActivity.LiveActivity.LiveHostAuthenticationActivity;
import com.mymusic.music.View.Activity.MyChildActivity.LiveActivity.LivelevelActivity;
import com.mymusic.music.base.BaseActivity;

import butterknife.OnClick;

public class MyLiveActivity extends BaseActivity {



    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_live);
    }

    @Override
    protected void LoadData() {

    }
    @OnClick({R.id.live_level,R.id.live_host,R.id.live_gift,R.id.live_history,R.id.back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.live_level:
                goActivity(LivelevelActivity.class);
                break;
            case R.id.live_host:
                goActivity(LiveHostAuthenticationActivity.class);
                break;
            case R.id.live_gift:
                goActivity(LiveGiftActivity.class);
                break;
            case R.id.live_history:
                goActivity(MyhistoryActivity.class);
                break;
        }

    }


    /**
     * 统一跳转
     * @param clazz
     */
    public void goActivity(Class<?> clazz){
        Intent intent = new Intent(MyLiveActivity.this, clazz);
        startActivity(intent);
    }

}
