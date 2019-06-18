package com.mymusic.music.View.Activity.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mymusic.music.Live;
import com.mymusic.music.MainActivity;
import com.mymusic.music.R;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_userName)
    EditText userName;
    @BindView(R.id.login_userPwd)
    EditText userPwd;
    @BindView(R.id.bt_login)
    EditText login;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void LoadData() {

    }

    @OnClick({R.id.bt_login,R.id.phone_login,R.id.forget_pwd})
    public void OnclickEvent(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                if (userName.getText().toString().equals("") || userPwd.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "请完善内容", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("username", userName.getText().toString());
                    map.put("password", userPwd.getText().toString());
                    NetRequest.postFormRequest(UrlManager.Login, map, new NetRequest.DataCallBack() {
                        @Override
                        public void requestSuccess(String result) throws Exception {
                            Live.getInstance().put(LoginActivity.this,result);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void requestFailure(Request request, IOException e) {
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                }
            case R.id.phone_login:
                Intent intent = new Intent(LoginActivity.this, LoginPhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.forget_pwd:
                Intent intent1 = new Intent(LoginActivity.this, ForgetPwdActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
