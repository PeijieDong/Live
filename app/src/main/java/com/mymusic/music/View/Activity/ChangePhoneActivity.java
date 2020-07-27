package com.mymusic.music.View.Activity;

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
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Login.RegisterActivity;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class ChangePhoneActivity extends BaseActivity {


    @BindView(R.id.get_code)
    TextView GetCode;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.nowPhone)
    TextView nowPhone;
    String phone = "";
    @Override
    protected void initVariables(Intent intent) {
        phone = Live.getInstance().getUser(this).getData().getUser_login();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_change_phone);
        next.setClickable(false);
    }

    @Override
    protected void LoadData() {
        if(phone != null){
            if(phone.length() == 11){
                StringBuilder builder = new StringBuilder(phone);
                builder.replace(3,7,"****");
                nowPhone.setText(builder.toString());
            }else{
                nowPhone.setText("手机号码错误");
            }
        } else{
            nowPhone.setText("手机号码错误");
        }
        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(code.getText().toString().trim().equals("")){
                    next.setBackgroundResource(R.drawable.login_normal);
                    next.setClickable(false);
                }else{
                    next.setBackgroundResource(R.drawable.login_press);
                    next.setClickable(true);
                }
            }
        });
    }

    @OnClick({R.id.back,R.id.get_code,R.id.next})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.get_code:
                initCode();
                CountDownTimer timer = new CountDownTimer(60000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        GetCode.setText(millisUntilFinished/1000+"s");
                    }

                    @Override
                    public void onFinish() {
                        GetCode.setText("重新获取");
                    }
                };
                timer.start();
                break;
            case R.id.next:
                if(code.getText().toString().equals("")){
                    ToastUtil.show(this,"请输入验证码",1);
                    return;
                }
                initCheck();
                break;
        }
    }

    private void initCheck() {
        loading();
        HashMap<String, String> map = new HashMap<>();
        map.put("code",code.getText().toString());
        NetRequest.postFormRequest(UrlManager.CHECK_PHONE, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33",result);
                BackCode backCode = GsonUtil.GsonToBean(result, BackCode.class);
                if(backCode.getMsg().equals("验证失败")){
                    ToastUtil.show(ChangePhoneActivity.this,backCode.getMsg(),1);
                }else {
                    Intent intent = new Intent(ChangePhoneActivity.this, BindPhoneActivity.class);
                    intent.putExtra("nowphone",nowPhone.getText().toString());
                    startActivity(intent);
                }
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(ChangePhoneActivity.this,"发送失败，请检查网络",1);
            }

            @Override
            public void TokenFail() {

            }
        });
        hideloading();
    }

    private void initCode() {
        loading();
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile",phone);
//        number = phone.getText().toString();
        NetRequest.postFormRequest(UrlManager.GET_CODE, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33",result);
                ToastUtil.show(ChangePhoneActivity.this,"发送成功，请注意查收",1);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(ChangePhoneActivity.this,"发送失败，请检查网络",1);
            }

            @Override
            public void TokenFail() {

            }
        });
        hideloading();
    }
}
