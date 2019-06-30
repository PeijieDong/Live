package com.mymusic.music.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mymusic.music.MainActivity;
import com.mymusic.music.R;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.View.Activity.MyChildActivity.SettingActivity.LockScreenActivity;
import com.mymusic.music.base.BaseActivity;

public class LaunchAcvtivity extends BaseActivity {


    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_launch_acvtivity);
    }

    @Override
    protected void LoadData() {
        Intent intent = null;
        if(!SharedPrefrenceUtils.getString(this,"Password").equals("")){
            intent = new Intent(this, LockScreenActivity.class);
        }else{
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
    }
}
