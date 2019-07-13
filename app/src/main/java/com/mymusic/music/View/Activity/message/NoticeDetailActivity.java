package com.mymusic.music.View.Activity.message;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mymusic.music.DataBean.NoticeMess;
import com.mymusic.music.R;
import com.mymusic.music.base.BaseActivity;

import java.io.Serializable;

import butterknife.BindView;

public class NoticeDetailActivity extends BaseActivity {
    NoticeMess.DataBean.ListBean bean;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.person)
    TextView person;

    @Override
    protected void initVariables(Intent intent) {
        bean = (NoticeMess.DataBean.ListBean)intent.getSerializableExtra("bean");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notice_detail);
    }

    @Override
    protected void LoadData() {
        title.setText(bean.getTitle());
        content.setText(bean.getContent());
        time.setText(bean.getCreatetime());
        person.setText("官方喇叭");
    }
}
