package com.mymusic.music.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mymusic.music.DataBean.UserDetail;
import com.mymusic.music.Live;
import com.mymusic.music.MainActivity;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.MyChildActivity.SettingActivity.LockScreenActivity;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Request;

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
        if(Live.getInstance().getUser(this)!=null && Live.getInstance().getToken(this) != null){
            initNet();
        }
        Intent intent = null;
        if(!SharedPrefrenceUtils.getString(this,"Password").equals("")){
            intent = new Intent(LaunchAcvtivity.this, LockScreenActivity.class);
        }else{
            intent = new Intent(LaunchAcvtivity.this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid",Live.getInstance().getUser(this).getData().getId());
        NetRequest.postFormHeadRequest(UrlManager.User_Detail, map, Live.getInstance().getToken(LaunchAcvtivity.this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                UserDetail bean = GsonUtil.GsonToBean(result, UserDetail.class);
                if(bean.getStatus() == -997){
                    Toast.makeText(LaunchAcvtivity.this,"登录过期，请重新登录",Toast.LENGTH_SHORT).show();
                    Live.getInstance().clear(LaunchAcvtivity.this);
                    Intent intent = new Intent(LaunchAcvtivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = null;
                    if(!SharedPrefrenceUtils.getString(LaunchAcvtivity.this,"Password").equals("")){
                        intent = new Intent(LaunchAcvtivity.this, LockScreenActivity.class);
                    }else{
                        intent = new Intent(LaunchAcvtivity.this, MainActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }
}
