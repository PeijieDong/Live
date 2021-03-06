package com.mymusic.music.View.Activity.message;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.Audit;
import com.mymusic.music.DataBean.NoticeMess;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.AuditRcAdapter;
import com.mymusic.music.View.Adapter.NoticeRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class ViolationActivity extends BaseActivity {


    @BindView(R.id.Rc)
    RecyclerView Rc;
    String position;

    @Override
    protected void initVariables(Intent intent) {
        position = intent.getStringExtra("position");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_violation);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("type",position);
        NetRequest.postFormHeadRequest(UrlManager.Notice_Mes, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                NoticeMess bean = GsonUtil.GsonToBean(result, NoticeMess.class);
                Rc.setLayoutManager(new LinearLayoutManager(ViolationActivity.this));
                NoticeRcAdapter adapter = new NoticeRcAdapter(R.layout.notice_item_layout,bean.getData().getList());
                adapter.setEmptyView(LayoutInflater.from(ViolationActivity.this).inflate(R.layout.empty_layout,null));
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(ViolationActivity.this, NoticeDetailActivity.class);
                        intent.putExtra("bean",(Serializable) bean.getData().getList().get(position));
                        startActivity(intent);
                    }
                });
                Rc.setAdapter(adapter);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Rc.setLayoutManager(new LinearLayoutManager(ViolationActivity.this));
                NoticeRcAdapter adapter = new NoticeRcAdapter(R.layout.notice_item_layout,null);
                adapter.setEmptyView(LayoutInflater.from(ViolationActivity.this).inflate(R.layout.empty_layout,null));
                Rc.setAdapter(adapter);
            }

            @Override
            public void TokenFail() {
                Intent intent = new Intent(ViolationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    @OnClick({R.id.back})
    public void Click(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
