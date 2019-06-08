package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mymusic.music.R;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Request;

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.detail_Rc)
    RecyclerView recyclerView;

    @Override
    protected void initVariables(Intent intent) {
        String id = intent.getStringExtra("id");
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        NetRequest.getFormRequest(UrlManager.HOME_DETAILS, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_details);
    }

    @Override
    protected void LoadData() {

    }
}
