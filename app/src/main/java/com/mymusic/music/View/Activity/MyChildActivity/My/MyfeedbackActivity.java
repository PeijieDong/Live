package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mymusic.music.R;
import com.mymusic.music.View.Activity.Community.CommunityAdviceActivity;
import com.mymusic.music.View.Activity.Community.CommunityReportActivity;
import com.mymusic.music.View.Activity.HistoryFeedBackActivity;
import com.mymusic.music.base.BaseActivity;

import butterknife.OnClick;

public class MyfeedbackActivity extends BaseActivity {


    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_myfeedback);
    }

    @Override
    protected void LoadData() {

    }


    @OnClick({R.id.community_advice,R.id.community_report,R.id.back,R.id.feedback_history})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.community_advice:
                Intent intent = new Intent(this, CommunityAdviceActivity.class);
                startActivity(intent);
                break;
            case R.id.community_report:
                Intent intent1 = new Intent(this, CommunityReportActivity.class);
                startActivity(intent1);
                break;
            case R.id.feedback_history:
                Intent intent2 = new Intent(this, HistoryFeedBackActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
