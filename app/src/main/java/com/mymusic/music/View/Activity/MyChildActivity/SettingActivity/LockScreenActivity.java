package com.mymusic.music.View.Activity.MyChildActivity.SettingActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.mymusic.music.R;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LockScreenActivity extends BaseActivity {

    @BindView(R.id.pattern_lock_view)
    PatternLockView mPatternLockView;
    @BindView(R.id.lock_text)
    TextView lockToast;
    List<List<PatternLockView.Dot>> lockPassword = new ArrayList<>();

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_lock_screen);
        if(!SharedPrefrenceUtils.getString(LockScreenActivity.this,"Password").equals("")){
            lockToast.setText(getResources().getString(R.string.lock_open));
        }else{
            lockToast.setText(getResources().getString(R.string.lock_text));
        }
    }

    @Override
    protected void LoadData() {
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);
    }

    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(), "Pattern drawing started");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(), "Pattern progress: " +
                    PatternLockUtils.patternToString(mPatternLockView, progressPattern));
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            if (!SharedPrefrenceUtils.getString(LockScreenActivity.this, "Password").equals("")) {

                if(SharedPrefrenceUtils.getString(LockScreenActivity.this,"Password")
                        .equals(PatternLockUtils.patternToString(mPatternLockView, pattern))){
                    finish();
                }else{
                    lockToast.setText(getResources().getString(R.string.lock_error));
                }
            } else {

                //设置密码
                lockPassword.add(pattern);
                if (lockPassword.size() == 1) {
                    lockToast.setText(getResources().getString(R.string.lock_toast));
                }
                if (lockPassword.size() == 2 &&
                        !PatternLockUtils.patternToString(mPatternLockView, lockPassword.get(0))
                                .equals(PatternLockUtils.patternToString(mPatternLockView, lockPassword.get(1)))) {
                    Toast.makeText(LockScreenActivity.this, "两次绘制不一致", Toast.LENGTH_SHORT).show();
                    SharedPrefrenceUtils.clearn(LockScreenActivity.this, "Password");
                } else if (lockPassword.size() == 2 &&
                        PatternLockUtils.patternToString(mPatternLockView, lockPassword.get(0))
                                .equals(PatternLockUtils.patternToString(mPatternLockView, lockPassword.get(1)))) {
                    SharedPrefrenceUtils.saveString(LockScreenActivity.this, "Password"
                            , PatternLockUtils.patternToString(mPatternLockView, pattern));
                    Toast.makeText(LockScreenActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            Log.d(getClass().getName(), "Pattern complete: " +
                    PatternLockUtils.patternToString(mPatternLockView, pattern));
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Pattern has been cleared");
        }
    };
}
