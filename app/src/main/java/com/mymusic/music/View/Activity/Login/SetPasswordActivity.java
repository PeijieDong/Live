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

import com.google.gson.Gson;
import com.mymusic.music.DataBean.BackCode;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
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
    private String code;
    private String yaoqingma;

    @Override
    protected void initVariables(Intent intent) {
        phone = intent.getStringExtra("phone");
        yaoqingma = intent.getStringExtra("yaoqingma");
        isregister = intent.getBooleanExtra("isregister", false);
        code = intent.getStringExtra("code");
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

    @OnClick({R.id.sure,R.id.back})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.sure:
                if(pwd.getText().toString().length()< 6 && !pwd.getText().toString().equals(pwd2)){
                    ToastUtil.show(this,"密码不一致且密码不可以小于6位",Toast.LENGTH_SHORT);
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
        map.put("code",code);
        map.put("invitacode",yaoqingma);
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
                BackCode backCode = GsonUtil.GsonToBean(result, BackCode.class);
                if(backCode.getStatus().equals("1")){
                    ToastUtil.show(SetPasswordActivity.this,"注册成功",Toast.LENGTH_SHORT);
                    finish();
                }else{
                    ToastUtil.show(SetPasswordActivity.this,backCode.getMsg(),Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(SetPasswordActivity.this,e.getMessage(),Toast.LENGTH_SHORT);
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }

    private void initForget() {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("username",phone);
        map1.put("user_pass",pwd.getText().toString());
        map1.put("user_pass2",pwd2.getText().toString());
        map1.put("code",code);
        NetRequest.postFormRequest(UrlManager.Forget, map1, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                BackCode backCode = GsonUtil.GsonToBean(result, BackCode.class);
                if(backCode.getStatus().equals("1")){
                    ToastUtil.show(SetPasswordActivity.this,"修改成功",Toast.LENGTH_SHORT);
                    finish();
                }else{
                    ToastUtil.show(SetPasswordActivity.this,backCode.getMsg(),Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(SetPasswordActivity.this,e.getMessage(),Toast.LENGTH_SHORT);
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
}
