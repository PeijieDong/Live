package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mymusic.music.R;
import com.mymusic.music.View.Activity.message.AuditActivity;
import com.mymusic.music.View.Activity.message.CommendActivity;
import com.mymusic.music.View.Activity.message.FeedbackActivity;
import com.mymusic.music.View.Activity.message.NoticeActivity;
import com.mymusic.music.View.Activity.message.SettingActivity;
import com.mymusic.music.View.Activity.message.ViolationActivity;
import com.mymusic.music.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MymessageActivity extends BaseActivity {

    @BindView(R.id.message_setting)
    TextView setting;
    @BindView(R.id.message_audit)
    ConstraintLayout audit;
    @BindView(R.id.message_violations)
    ConstraintLayout violations;
    @BindView(R.id.message_feedback)
    ConstraintLayout feedback;
    @BindView(R.id.message_commend)
    ConstraintLayout commend;
    @BindView(R.id.message_notice)
    ConstraintLayout notice;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mymessage);
    }

    @Override
    protected void LoadData() {

    }

    @OnClick({R.id.message_setting,R.id.message_audit,R.id.message_violations,R.id.message_feedback,R.id.message_commend
    ,R.id.message_notice})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.message_setting:
                goActivity(SettingActivity.class);
                break;
            case R.id.message_audit:
                goActivity(AuditActivity.class);
                break;
            case R.id.message_violations:
                goActivity(ViolationActivity.class);
                break;
            case R.id.message_feedback:
                goActivity(FeedbackActivity.class);
                break;
            case R.id.message_commend:
                goActivity(CommendActivity.class);
                break;
            case R.id.message_notice:
                goActivity(NoticeActivity.class);
                break;
        }
    }

    public void goActivity(Class<?> clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
