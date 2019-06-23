package com.mymusic.music.View.Activity.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.User;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.base.BaseActivity;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivity extends BaseActivity {

    User user;
    @BindView(R.id.head)
    CircleImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sign)
    TextView sign;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.birthday)
    TextView birthday;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user);
    }

    @Override
    protected void LoadData() {
        initView();
    }

    private void initView() {
        user = Live.getInstance().get(this);
        Glide.with(this).load(user.getList().getAvatar()).into(head);
        name.setText(user.getList().getUser_nicename());
        sex.setText(user.getList().getSex());
        birthday.setText(user.getList().getBirthday());
    }
}
