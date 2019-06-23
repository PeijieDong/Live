package com.mymusic.music.View.Activity.Login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class SetPasswordActivity extends BaseActivity {

    String phone;
    @BindView(R.id.register_userName)
    EditText pwd;
    @BindView(R.id.register_userPwd)
    EditText pwd2;
    @BindView(R.id.sure)
    Button go;
    boolean isregister = false;

    @Override
    protected void initVariables(Intent intent) {
        phone = intent.getStringExtra("phone");
        isregister = intent.getBooleanExtra("isregister", false);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_set_password);
        go.setClickable(false);
    }

    @Override
    protected void LoadData() {
        pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!pwd.getText().toString().equals("") && !pwd2.getText().toString().equals("")){
                    go.setBackgroundResource(R.drawable.login_press);
                    go.setClickable(true);
                }else{
                    go.setBackgroundResource(R.drawable.login_normal);
                    go.setClickable(false);
                }
            }
        });
        pwd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!pwd.getText().toString().equals("") && !pwd2.getText().toString().equals("")){
                    go.setBackgroundResource(R.drawable.login_press);
                    go.setClickable(true);
                }else{
                    go.setBackgroundResource(R.drawable.login_normal);
                    go.setClickable(false);
                }
            }
        });
    }

    @OnClick({R.id.sure})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.sure:
                if(pwd.getText().toString().length()<6 && !pwd.getText().toString().equals(pwd2)){
                    Toast.makeText(this,"密码不正确",Toast.LENGTH_SHORT).show();
                }else{
                    initNet();
                }
                break;
        }
    }
    HashMap<String, String> map;
    private void initNet() {
        map = new HashMap<>();
        map.put("username",phone);
        map.put("password",pwd.getText().toString());
        if(isregister){
            initRegister();
        }else{
            initForget();
        }
    }

    private void initRegister() {
        NetRequest.postFormRequest(UrlManager.Register, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(SetPasswordActivity.this,"成功",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(SetPasswordActivity.this,"失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initForget() {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("username",phone);
        map1.put("user_pass",pwd.getText().toString());
        map1.put("user_pass2",pwd2.getText().toString());
        map1.put("code","1234");
        NetRequest.postFormRequest(UrlManager.Forget, map1, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(SetPasswordActivity.this,"成功",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(SetPasswordActivity.this,"失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
