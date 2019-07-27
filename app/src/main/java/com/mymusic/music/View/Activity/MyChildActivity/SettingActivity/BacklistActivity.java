package com.mymusic.music.View.Activity.MyChildActivity.SettingActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mymusic.music.R;
import com.mymusic.music.View.Activity.ChangePhoneActivity;
import com.mymusic.music.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class BacklistActivity extends BaseActivity {

    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.change_phone)
    Button changePhone;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_backlist);
    }

    @Override
    protected void LoadData() {

    }
    @OnClick({R.id.back,R.id.change_phone})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.change_phone:
                Intent intent = new Intent(this,ChangePhoneActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
