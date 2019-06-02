package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mymusic.music.R;
import com.mymusic.music.View.Activity.MyChildActivity.SettingActivity.AccountActivity;
import com.mymusic.music.View.Activity.MyChildActivity.SettingActivity.BacklistActivity;
import com.mymusic.music.View.Activity.MyChildActivity.SettingActivity.LanguageActivity;
import com.mymusic.music.View.Activity.MyChildActivity.SettingActivity.LockScreenActivity;
import com.mymusic.music.View.Activity.user.UserActivity;
import com.mymusic.music.base.BaseActivity;

import butterknife.OnClick;

public class MysettingActivity extends BaseActivity {


    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mysetting);
    }

    @Override
    protected void LoadData() {

    }
    @OnClick({R.id.setting_information,R.id.setting_accountSecurity,R.id.setting_lockScreen,
            R.id.setting_language,R.id.setting_clearCache,R.id.setting_version,R.id.setting_backlist,
            R.id.setting_aboutOur,R.id.setting_loginBack})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.setting_information:
                goActivity(UserActivity.class);
                break;
            case R.id.setting_accountSecurity:
                goActivity(AccountActivity.class);
                break;
            case R.id.setting_lockScreen:
                goActivity(LockScreenActivity.class);
                break;
            case R.id.setting_language:
                goActivity(LanguageActivity.class);
                break;
            case R.id.setting_clearCache:

                break;
            case R.id.setting_version:

                break;
            case R.id.setting_backlist:
                goActivity(BacklistActivity.class);
                break;
            case R.id.setting_aboutOur:
                goActivity(MyaboutActivity.class);
                break;
            case R.id.setting_loginBack:

                break;
        }
    }

    /**
     * 统一跳转
     * @param clazz
     */
    public void goActivity(Class<?> clazz){
        Intent intent = new Intent(MysettingActivity.this, clazz);
        startActivity(intent);
    }
}
