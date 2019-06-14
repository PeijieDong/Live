package com.mymusic.music.View.Activity.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mymusic.music.R;
import com.mymusic.music.base.BaseActivity;

import butterknife.BindView;

public class ListUserActivity extends BaseActivity {

    @BindView(R.id.list_rc)
    RecyclerView listRc;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list_user);
    }

    @Override
    protected void LoadData() {
        listRc.setLayoutManager(new LinearLayoutManager(this));
    }
}
