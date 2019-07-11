package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.FeedBack;
import com.mymusic.music.R;
import com.mymusic.music.base.BaseActivity;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

public class HistoryFeedDetail extends BaseActivity {

    private FeedBack.DataBean.ListBean feedback;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.time)
    TextView time;
    @Override
    protected void initVariables(Intent intent) {
        feedback = (FeedBack.DataBean.ListBean) intent.getSerializableExtra("feedback");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_history_feed_detail);
    }

    @Override
    protected void LoadData() {
        title.setText(feedback.getContent());
        time.setText(feedback.getAddtime());
        Glide.with(this).load(feedback.getImages()).into(image);
    }
    @OnClick({R.id.back})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
