package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.UserBean;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.base.BaseActivity;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyexchangeActivity extends BaseActivity {

    @BindView(R.id.head)
    CircleImageView head;
    @BindView(R.id.name)
    TextView name;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_myexchange);
    }

    @Override
    protected void LoadData() {
        UserBean user = Live.getInstance().getUser(this);
        Glide.with(this).load(user.getData().getAvatar()).into(head);
        name.setText(user.getData().getUser_nicename());
    }
}
