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
import android.widget.TextView;

import com.mymusic.music.R;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.register_userName)
    EditText phone;
    @BindView(R.id.register_userPwd)
    EditText code;
//    @BindView(R.id.get_code)
//    TextView getCode;
    @BindView(R.id.bt_login)
    Button go;
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
                if(!code.getText().toString().equals("") && !phone.getText().toString().equals("")){
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

    @OnClick({R.id.bt_login,R.id.close})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.close:
                finish();
                break;
            case R.id.bt_login:
                initRegister();
                break;
//            case R.id.get_code:
//                CountDownTimer timer = new CountDownTimer(60000, 1000) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        getCode.setText(millisUntilFinished/1000+"s");
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        getCode.setText("重新获取");
//                    }
//                };
//                timer.start();
//                break;
        }
    }

    private void initRegister() {
        Intent intent = new Intent(this, SetPasswordActivity.class);
        intent.putExtra("phone",phone.getText().toString());
        intent.putExtra("isregister",true);
        startActivity(intent);
        finish();
    }

}
