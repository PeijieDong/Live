package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
        initView();
    }

    private void initNet() {
        NetRequest.postFormHeadRequest(UrlManager.GetFans, null, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
//                Fans fans = GsonUtil.GsonToBean(result, Fans.class);
                Log.e("33",result);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initView() {
        List<String> list = new ArrayList<>();
        rc.setLayoutManager(new LinearLayoutManager(this));
        FansRcAdapter adapter = new FansRcAdapter(R.layout.focus_rc_layout,list);
        rc.setAdapter(adapter);
    }
}
