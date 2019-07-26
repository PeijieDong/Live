package com.mymusic.music.View.Activity.Login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mymusic.music.DataBean.BackCode;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class RegisterActivity extends BaseActivity {

    private boolean STATE = false;
    @BindView(R.id.register_userName)
    EditText phone;
    @BindView(R.id.register_userPwd)
    EditText code;
    @BindView(R.id.getCode)
    TextView getCode;
    @BindView(R.id.bt_login)
    Button go;
    @BindView(R.id.et_yaoqing)
    EditText yaoqingma;
    private String number ="";
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        go.setClickable(false);
    }

    @Override
    protected void LoadData() {
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if( !phone.getText().toString().equals("")){
                    go.setBackgroundResource(R.drawable.login_press);
                    go.setClickable(true);
                }else{
                    go.setBackgroundResource(R.drawable.login_normal);
                    go.setClickable(false);
                }
            }
        });
        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!code.getText().toString().equals("") && !phone.getText().toString().equals("")){
                    go.setBackgroundResource(R.drawable.login_press);
                    go.setClickable(true);
                }else{
                    go.setBackgroundResource(R.drawable.login_normal);
                    go.setClickable(false);
                }
            }
        });
    }

    @OnClick({R.id.bt_login,R.id.close,R.id.getCode})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.close:
                finish();
                break;
            case R.id.bt_login:
                if(phone.getText().toString().equals("")){
                    ToastUtil.show(this,"请输入手机号",1);
                    return;
                }
                if(code.getText().toString().equals("")){
                    ToastUtil.show(this,"请输入验证码",1);
                    return;
                }
                initRegister();
                break;
            case R.id.getCode:
                if(phone.getText().toString().equals("")){
                    ToastUtil.show(this,"请输入手机号",1);
                   return;
                }
                initCode();
                CountDownTimer timer = new CountDownTimer(60000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        getCode.setText(millisUntilFinished/1000+"s");
                    }

                    @Override
                    public void onFinish() {
                        getCode.setText("重新获取");
                    }
                };
                timer.start();
                break;
        }
    }

    private void initCode() {
        loading();
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile",phone.getText().toString());
        number = phone.getText().toString();
        NetRequest.postFormRequest(UrlManager.GET_CODE, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33",result);
                BackCode backCode = GsonUtil.GsonToBean(result, BackCode.class);
                if(backCode.getStatus().equals("2")){
                    ToastUtil.show(RegisterActivity.this,"该账号已被注册",1);
                    return;
                }else {
                    STATE = true;
                }
                ToastUtil.show(RegisterActivity.this,"发送成功，请注意查收",1);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(RegisterActivity.this,"发送失败，请检查网络",1);
            }

            @Override
            public void TokenFail() {

            }
        });
        hideloading();
    }

    private void initRegister() {
        if(!STATE){
            ToastUtil.show(RegisterActivity.this,"请先获取验证码",1);
        }else{
            Intent intent = new Intent(RegisterActivity.this, SetPasswordActivity.class);
            intent.putExtra("phone",number);
            intent.putExtra("code",code.getText().toString());
            intent.putExtra("yaoqingma",yaoqingma.getText().toString());
            intent.putExtra("isregister",true);
            startActivity(intent);
            finish();
        }
    }

}
