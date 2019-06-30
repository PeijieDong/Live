package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.OnClick;
import okhttp3.Request;

public class MoneyDetailActivity extends BaseActivity {


    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_money_detail);
    }

    @Override
    protected void LoadData() {
        initNet("");
    }

    private void initNet(String type) {
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("token",Live.getInstance().getToken(this));
        map.put("type",type);
        map.put("page","1");
        NetRequest.postFormHeadRequest(UrlManager.Money_Detail, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    @OnClick({R.id.all_money,R.id.put_money,R.id.get_money})
    public void EventClick(View view){
        switch (view.getId()){
            case R.id.all_money:
                break;
            case R.id.put_money:
                break;
            case R.id.get_money:
                break;
        }
    }
}
