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
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class BindPhoneActivity extends BaseActivity {
    @BindView(R.id.get_code)
    TextView GetCode;
    @BindView(R.id.change_phone)
    TextView changePhone;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.sure)
    Button sure;
    @BindView(R.id.nowPhone)
    TextView nowPhone;
    String nowphone;
    @Override
    protected void initVariables(Intent intent) {
        nowphone = intent.getStringExtra("nowphone");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_bind_phone);
    }

    @Override
    protected void LoadData() {
        sure.setClickable(false);
        nowPhone.setText(nowphone);
        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(code.getText().toString().trim().equals("")||changePhone.getText().toString().equals("")){
                    sure.setBackgroundResource(R.drawable.login_normal);
                    sure.setClickable(false);
                }else{
                    sure.setBackgroundResource(R.drawable.login_press);
                    sure.setClickable(true);
                }
            }
        });
        changePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(code.getText().toString().trim().equals("")||changePhone.getText().toString().equals("")){
                    sure.setBackgroundResource(R.drawable.login_normal);
                    sure.setClickable(false);
                }else{
                    sure.setBackgroundResource(R.drawable.login_press);
                    sure.setClickable(true);
                }
            }
        });
    }

    @OnClick({R.id.back,R.id.get_code,R.id.sure})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.get_code:
                if(changePhone.getText().toString().equals("")){
                    ToastUtil.show(this,"请输入手机号",1);
                    return;
                }
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
            case R.id.sure:
                if(changePhone.getText().toString().equals("")){
                    ToastUtil.show(this,"请输入手机号",1);
                    return;
                }
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
        map.put("username",changePhone.getText().toString());
        map.put("code",code.getText().toString());
        NetRequest.postFormRequest(UrlManager.CHANGE_PHONE, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33",result);
                BackCode backCode = GsonUtil.GsonToBean(result, BackCode.class);
                if(backCode.getStatus().equals("1")){
                    ToastUtil.show(BindPhoneActivity.this,"绑定成功",1);
                    finish();
                }else{
                    ToastUtil.show(BindPhoneActivity.this,backCode.getMsg(),1);
                }
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(BindPhoneActivity.this,"发送失败，请检查网络",1);
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
        map.put("mobile",changePhone.getText().toString());
        NetRequest.postFormRequest(UrlManager.GET_CODE, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33",result);
                ToastUtil.show(BindPhoneActivity.this,"发送成功，请注意查收",1);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(BindPhoneActivity.this,"发送失败，请检查网络",1);
            }

            @Override
            public void TokenFail() {

            }
        });
        hideloading();
    }
}
