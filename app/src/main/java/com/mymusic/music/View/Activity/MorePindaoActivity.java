package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.PinDao;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.MoreRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class MorePindaoActivity extends BaseActivity {

    @BindView(R.id.rc)
    RecyclerView RC;


    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_more_pindao);
    }

    @Override
    protected void LoadData() {
        initTab();
    }

    private void initTab() {
        loading();
        NetRequest.getFormRequest(UrlManager.GET_PINDAO, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                hideloading();
                PinDao bean = GsonUtil.GsonToBean(result, PinDao.class);
                initRc(bean);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                hideloading();
            }

            @Override
            public void TokenFail() {
                hideloading();
            }
        });

    }
    private void initRc(PinDao bean) {
        RC.setLayoutManager(new LinearLayoutManager(this));
        MoreRcAdapter adapter = new MoreRcAdapter(R.layout.grid_item_layout,bean.getData().getList());
        RC.setAdapter(adapter);
    }
    @OnClick(R.id.close)
    public void ClickEvent(View view){
        finish();
    }
}
