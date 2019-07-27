package com.mymusic.music.View.Activity.MyChildActivity.SettingActivity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mymusic.music.DataBean.User;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.View.Activity.Login.ForgetPwdActivity;
import com.mymusic.music.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountActivity extends BaseActivity {

    @BindView(R.id.setting_aboutOur)
    ConstraintLayout changePwd;
    @BindView(R.id.phone)
    TextView phone;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_account);
    }

    @Override
    protected void LoadData() {
        User user = Live.getInstance().get(this);
        phone.setText(user.getList().getUser_login());
    }

    @OnClick({R.id.setting_aboutOur,R.id.back,R.id.setting_backlist})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.setting_aboutOur:
                Intent intent = new Intent(this, ForgetPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.setting_backlist:
                Intent intent2 = new Intent(this, BacklistActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
