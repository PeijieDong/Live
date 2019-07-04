package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mymusic.music.DataBean.MoneyDetail;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.MoneyDetailAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class MoneyDetailActivity extends BaseActivity {

    List<TextView> list = new ArrayList<>();
    @BindView(R.id.all_money)
    TextView allMoney;
    @BindView(R.id.put_money)
    TextView putMoney;
    @BindView(R.id.get_money)
    TextView getMoney;
    @BindView(R.id.Rc)
    RecyclerView rc;
    MoneyDetail bean;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_money_detail);
    }

    @Override
    protected void LoadData() {
        list.add(allMoney);
        list.add(putMoney);
        list.add(getMoney);
        initNet("");
    }

    private void initNet(String type) {
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("type",type);
        map.put("page","1");
        NetRequest.postFormHeadRequest(UrlManager.Money_Detail, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                bean = GsonUtil.GsonToBean(result, MoneyDetail.class);
                initRc();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initRc() {
        rc.setLayoutManager(new LinearLayoutManager(this));
        MoneyDetailAdapter adapter = new MoneyDetailAdapter(R.layout.moner_detail_layout,bean.getData().getList());
        rc.setAdapter(adapter);
    }

    @OnClick({R.id.all_money,R.id.put_money,R.id.get_money})
    public void EventClick(View view){
        switch (view.getId()){
            case R.id.all_money:
                for (int i = 0 ;i<list.size();i++){
                    if(list.get(i).getId() == R.id.all_money){
                        list.get(i).setTextColor(ContextCompat.getColor(this,R.color.white));
                        list.get(i).setBackgroundResource(R.drawable.focus);
                        initNet("");
                    }else{
                        list.get(i).setTextColor(ContextCompat.getColor(this,R.color.blank));
                        list.get(i).setBackgroundResource(R.drawable.money_detail);
                    }
                }
                break;
            case R.id.put_money:
                for (int i = 0 ;i<list.size();i++){
                    if(list.get(i).getId() == R.id.put_money){
                        list.get(i).setTextColor(ContextCompat.getColor(this,R.color.white));
                        list.get(i).setBackgroundResource(R.drawable.focus);
                        initNet("1");
                    }else{
                        list.get(i).setTextColor(ContextCompat.getColor(this,R.color.blank));
                        list.get(i).setBackgroundResource(R.drawable.money_detail);
                    }
                }
                break;
            case R.id.get_money:
                for (int i = 0 ;i<list.size();i++){
                    if(list.get(i).getId() == R.id.get_money){
                        list.get(i).setTextColor(ContextCompat.getColor(this,R.color.white));
                        list.get(i).setBackgroundResource(R.drawable.focus);
                        initNet("3");
                    }else{
                        list.get(i).setTextColor(ContextCompat.getColor(this,R.color.blank));
                        list.get(i).setBackgroundResource(R.drawable.money_detail);
                    }
                }
                break;
        }
    }
}
