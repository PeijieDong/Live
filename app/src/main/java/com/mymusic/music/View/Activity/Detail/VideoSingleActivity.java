package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.R;
import com.mymusic.music.View.Adapter.SinglePlayRcAdapter;
import com.mymusic.music.base.BaseActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoSingleActivity extends BaseActivity {

    List<HomeData.DataBean.ListBean> playData = new ArrayList<>();
    @BindView(R.id.Rc)
    RecyclerView rc;

    @Override
    protected void initVariables(Intent intent) {
        if((HomeData.DataBean.ListBean) intent.getSerializableExtra("playData") != null){
            HomeData.DataBean.ListBean play = (HomeData.DataBean.ListBean) intent.getSerializableExtra("playData");
            playData.add(play);
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_video_single);
    }

    @Override
    protected void LoadData() {
        rc.setLayoutManager(new LinearLayoutManager(this));
        SinglePlayRcAdapter adapter = new SinglePlayRcAdapter(R.layout.video_three_layout,playData);
        rc.setAdapter(adapter);
    }

    @OnClick(R.id.back)
    public void back(View view){
        finish();
    }
}
