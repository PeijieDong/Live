package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.Fans;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.FansRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

public class MyfansActivity extends BaseActivity {

    @BindView(R.id.fans_Rc)
    RecyclerView rc;
    @BindView(R.id.fans_num)
    TextView num;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_myfans);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        NetRequest.postFormHeadRequest(UrlManager.GetFans, null, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Fans fans = GsonUtil.GsonToBean(result, Fans.class);
                num.setText("您总共有"+fans.getData().getTotal()+"个粉丝");
                initView(fans);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initView(Fans fans) {
        rc.setLayoutManager(new LinearLayoutManager(this));
        FansRcAdapter adapter = new FansRcAdapter(R.layout.focus_rc_layout,fans.getData().getList());
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TextView focus = view.findViewById(R.id.focus_rc_focusbt);
                if(focus.getText().toString().equals("+关注")){
                    focus.setText("取消关注");
                    focus.setBackgroundResource(R.drawable.back_friend_detail_cencelfocus);
                }else{
                    focus.setText("+关注");
                    focus.setBackgroundResource(R.drawable.back_friend_detail_focus);
                }

            }
        });
        rc.setAdapter(adapter);
    }
}
