package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mymusic.music.DataBean.History;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.HistoryRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
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
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MyhistoryActivity.this, DetailsActivity.class);
                intent.putExtra("id",bean.getData().getList().get(position).getHid());
                startActivity(intent);
            }
        });
        historyRc.setAdapter(adapter);
    }
    @OnClick({R.id.clear_history,R.id.back})
    public void ClickEvent(View view){
        switch (view.getId()) {
            case R.id.clear_history:
                initClear();
                break;
            case R.id.back:
                break;
        }
    }

    private void initClear() {
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(UrlManager.Clear_History, null, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(MyhistoryActivity.this,"操作成功",Toast.LENGTH_SHORT).show();
                initNet();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(MyhistoryActivity.this,"操作失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
