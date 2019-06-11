package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.FriendDetailTOP;
import com.mymusic.music.R;
import com.mymusic.music.base.BaseActivity;

import java.io.Serializable;

import butterknife.BindView;

public class FriendDetailTwoActivity extends BaseActivity {

    @BindView(R.id.detail_des)
    TextView describe;
    @BindView(R.id.detail_icon)
    ImageView icon;
    @BindView(R.id.detail_type)
    TextView type;
    @BindView(R.id.detail_name)
    TextView name;
    private FriendDetailTOP detail;

    @Override
    protected void initVariables(Intent intent) {

        detail = (FriendDetailTOP) intent.getSerializableExtra("frienddetail");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_friend_detail_two);
    }

    @Override
    protected void LoadData() {
        describe.setText(detail.getData().getList().getDescription());
        Glide.with(this).load(detail.getData().getList().getIcon()).into(icon);
        name.setText(detail.getData().getList().getTitle());
        type.setText(detail.getData().getList().getName());
    }
}
