package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.mymusic.music.DataBean.History;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.HistoryRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

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
        initNet();
    }

    private void initNet() {
        NetRequest.postFormHeadRequest(UrlManager.History, null, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                History bean = GsonUtil.GsonToBean(result, History.class);
                initView(bean);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initView(History bean) {
        historyRc.setLayoutManager(new LinearLayoutManager(this));
        HistoryRcAdapter adapter = new HistoryRcAdapter(R.layout.history_rc_item, bean.getData().getList());
        historyRc.setAdapter(adapter);
    }
}
