package com.mymusic.music.View.Activity.Login;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mymusic.music.DataBean.User;
import com.mymusic.music.Live;
import com.mymusic.music.MainActivity;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.message.NoticeActivity;
import com.mymusic.music.base.ActivityCollector;
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
    @BindView(R.id.phone_login)
    TextView phoneLogin;
    @BindView(R.id.bt_login)
    Button Login;
    @BindView(R.id.visible_pwd)
    ImageView pwdState;
    private int GONE = 0;
    private int VISIBLE = 1;
    private int STATE = GONE;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void LoadData() {
        Live.getInstance().clear(this);
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!userName.getText().toString().equals("")&& !userPwd.getText().toString().equals("")){
                    Login.setBackground(getResources().getDrawable(R.drawable.login_press));
                    Login.setClickable(true);
                }else{
                    Login.setBackground(getResources().getDrawable(R.drawable.login_normal));
                    Login.setClickable(false);
                }
            }
        });
        userPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!userName.getText().toString().equals("") && !userPwd.getText().toString().equals("")){
                    Login.setBackground(getResources().getDrawable(R.drawable.login_press));
                    Login.setClickable(true);
                }else{
                    Login.setBackground(getResources().getDrawable(R.drawable.login_normal));
                    Login.setClickable(false);
                }
            }
        });
    }

    @OnClick({R.id.bt_login,R.id.phone_login,R.id.forget_pwd,R.id.register_fast,R.id.close,R.id.clear_userName,R.id.visible_pwd})
    public void OnclickEvent(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.bt_login:
                if (userName.getText().toString().equals("") || userPwd.getText().toString().equals("")) {
                    ToastUtil.show(LoginActivity.this, "请完善内容", Toast.LENGTH_SHORT);
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("username", userName.getText().toString());
                    map.put("password", userPwd.getText().toString());
                    NetRequest.postFormRequest(UrlManager.Login, map, new NetRequest.DataCallBack() {
                        @Override
                        public void requestSuccess(String result) throws Exception {
                            Log.e("33",result);
                            User user = GsonUtil.GsonToBean(result, User.class);
                            if(user != null && user.getList()!=null && user.getList().getCode() != null){
                                String code = user.getList().getCode();
                                SharedPrefrenceUtils.saveString(LoginActivity.this,"code",code);
                            }
                            if(user.getMsg().equals("登录成功")){
                                Live.getInstance().put(LoginActivity.this,result);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                ToastUtil.show(LoginActivity.this,user.getMsg(),Toast.LENGTH_SHORT);
                            }
                        }

                        @Override
                        public void requestFailure(Request request, IOException e) {
                            ToastUtil.show(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT);
                        }
                        @Override
                        public void TokenFail() {
                            LoginDialog dialog = new LoginDialog(getActivity());
                            dialog.Show();
                        }
                    });
                    break;
                }
            case R.id.phone_login:
                if(phoneLogin.getText().toString().equals("手机号登录")){
                    phoneLogin.setText("账号登录");
                    userName.setHint("请输入手机号");
                    userPwd.setHint("请输入密码");
                    userName.setText("");
                    userPwd.setText("");
                }else if(phoneLogin.getText().toString().equals("账号登录")){
                    phoneLogin.setText("手机号登录");
                    userName.setHint("请输入账号");
                    userPwd.setHint("请输入密码");
                    userName.setText("");
                    userPwd.setText("");
                }
                break;
            case R.id.forget_pwd:
                Intent intent1 = new Intent(LoginActivity.this, ForgetPwdActivity.class);
                startActivity(intent1);
                break;
            case R.id.register_fast:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.clear_userName:
                userName.setText("");
                break;
            case R.id.visible_pwd:
                if(STATE == GONE){
                    STATE = VISIBLE;
                    pwdState.setImageResource(R.drawable.visibility);
                    userPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    STATE = GONE;
                    pwdState.setImageResource(R.drawable.visibility_off);
                    userPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
        }
    }
}
