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

public class ForgetPwdActivity extends BaseActivity {

    @BindView(R.id.register_userName)
    EditText phone;
    @BindView(R.id.register_userPwd)
    EditText pwd;
    @BindView(R.id.get_code)
    TextView code;
    @BindView(R.id.bt_login)
    Button go;
    private String number ="";
    private boolean STATE = false;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_forget_pwd);
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
                if(!phone.getText().toString().equals("")){
                    go.setBackgroundResource(R.drawable.login_press);
                    go.setClickable(true);
                }else{
                    go.setBackgroundResource(R.drawable.login_normal);
                    go.setClickable(false);
                }
            }
        });

    }

    @OnClick({R.id.bt_login,R.id.close,R.id.get_code})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.close:
                finish();
                break;
            case R.id.bt_login:
                initForget();
                break;
            case R.id.get_code:
                initCode();
                CountDownTimer timer = new CountDownTimer(60000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        code.setText(millisUntilFinished/1000+"s");
                    }

                    @Override
                    public void onFinish() {
                        code.setText("重新获取");
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
                    ToastUtil.show(ForgetPwdActivity.this,"该账号已被注册",1);
                    return;
                }else {
                    STATE = true;
                }
                ToastUtil.show(ForgetPwdActivity.this,"发送成功，请注意查收",1);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(ForgetPwdActivity.this,"发送失败，请检查网络",1);
            }

            @Override
            public void TokenFail() {

            }
        });
        hideloading();
    }

    private void initForget() {
        if(STATE){
            Intent intent = new Intent(this, SetPasswordActivity.class);
            intent.putExtra("phone",phone.getText().toString());
            startActivity(intent);
            finish();
        }else{
            ToastUtil.show(ForgetPwdActivity.this,"请先获取验证码",1);
        }
    }
}
