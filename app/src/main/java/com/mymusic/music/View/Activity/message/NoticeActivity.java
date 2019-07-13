package com.mymusic.music.View.Activity.message;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.NoticeMess;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.NoticeRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class NoticeActivity extends BaseActivity {

    @BindView(R.id.Rc)
    RecyclerView rc;
    String position = "1";
    @Override
    protected void initVariables(Intent intent) {
        position = intent.getStringExtra("position");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notice);
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
                rc.setLayoutManager(new LinearLayoutManager(NoticeActivity.this));
                NoticeRcAdapter adapter = new NoticeRcAdapter(R.layout.notice_item_layout,bean.getData().getList());
                adapter.setEmptyView(LayoutInflater.from(NoticeActivity.this).inflate(R.layout.empty_layout,null));
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(NoticeActivity.this, NoticeDetailActivity.class);
                        intent.putExtra("bean",(Serializable) bean.getData().getList().get(position));
                        startActivity(intent);
                    }
                });
                rc.setAdapter(adapter);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {
                Intent intent = new Intent(NoticeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.back)
    public void ClickBack(){
        finish();
    }
}
