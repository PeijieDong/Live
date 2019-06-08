package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mymusic.music.R;
import com.mymusic.music.View.Adapter.HistoryRcAdapter;
import com.mymusic.music.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyhistoryActivity extends BaseActivity {

    @BindView(R.id.my_history_rc)
    RecyclerView historyRc;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_myhistory);
    }

    @Override
    protected void LoadData() {
        List<String> list = new ArrayList<>();
        historyRc.setLayoutManager(new LinearLayoutManager(this));
        historyRc.setAdapter(new HistoryRcAdapter(R.layout.history_rc_item,list));
    }
}
