package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.mymusic.music.R;
import com.mymusic.music.base.BaseActivity;

import butterknife.BindView;

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.detail_Rc)
    RecyclerView recyclerView;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_details);
    }

    @Override
    protected void LoadData() {

    }
}
