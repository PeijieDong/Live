package com.mymusic.music.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mymusic.music.ActivityAdv;
import com.mymusic.music.DataBean.UserDetail;
import com.mymusic.music.Live;
import com.mymusic.music.MainActivity;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.MyChildActivity.SettingActivity.LockScreenActivity;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class LaunchAcvtivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    private static final int PERMISSIONS = 100;
    String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE ,
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_launch_acvtivity);
    }

    @Override
    protected void LoadData() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            checkLogin();
        } else {
            EasyPermissions.requestPermissions(this, "请授予权限",
                    PERMISSIONS, perms);
        }

    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid",Live.getInstance().getUser(this).getData().getId());
        NetRequest.postFormHeadRequest(UrlManager.User_Detail, map, Live.getInstance().getToken(LaunchAcvtivity.this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Intent intent = null;
                if(!SharedPrefrenceUtils.getString(LaunchAcvtivity.this,"Password").equals("")){
                    intent = new Intent(LaunchAcvtivity.this, LockScreenActivity.class);
                }else{
                    intent = new Intent(LaunchAcvtivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        checkLogin();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    public void checkLogin(){
        Intent intent = null;
        if(Live.getInstance().get(this) == null){
            Live.getInstance().clear(this);
            if(!SharedPrefrenceUtils.getString(this,"Password").equals("")){
                intent = new Intent(LaunchAcvtivity.this, LockScreenActivity.class);
            }else{
                intent = new Intent(LaunchAcvtivity.this, ActivityAdv.class);
            }
            startActivity(intent);
            finish();
        }else{
            intent = new Intent(LaunchAcvtivity.this, ActivityAdv.class);
            startActivity(intent);
            finish();
        }
    }
}
