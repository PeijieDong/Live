package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mymusic.music.R;
import com.mymusic.music.base.BaseActivity;

import java.io.Serializable;

public class FriendDetailTwoActivity extends BaseActivity {


    @Override
    protected void initVariables(Intent intent) {
        Serializable detail = intent.getSerializableExtra("frienddetail");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_friend_detail_two);
    }

    @Override
    protected void LoadData() {

    }
}
