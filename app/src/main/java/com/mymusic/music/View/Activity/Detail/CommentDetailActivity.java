package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.CommentData;
import com.mymusic.music.R;
import com.mymusic.music.base.BaseActivity;

import java.io.Serializable;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommentDetailActivity extends BaseActivity {

    @BindView(R.id.detail_head_cir)
    CircleImageView head;
    @BindView(R.id.detail_name)
    TextView name;
    @BindView(R.id.detail_time)
    TextView time;
    @BindView(R.id.detail_content)
    TextView content;
    private CommentData.DataBean.ListBean list;

    @Override
    protected void initVariables(Intent intent) {
        list = (CommentData.DataBean.ListBean) intent.getSerializableExtra("commentList");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_comment_detail);
    }

    @Override
    protected void LoadData() {
        name.setText(list.getUser_nicename());
        time.setText(list.getCreatetime());
        content.setText(list.getContent());
        Glide.with(this).load(list.getAvatar()).into(head);

    }
}
