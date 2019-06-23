package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.FriendAllData;
import com.mymusic.music.DataBean.FriendAllTitle;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LeftNavigation;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.FriendDetailActivity;
import com.mymusic.music.View.Adapter.FriendFoundRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

public class FriendFoundActivity extends BaseActivity {

    @BindView(R.id.leftNavigation)
    LeftNavigation navigation;
    @BindView(R.id.friend_found_rc)
    RecyclerView rc;
    FriendAllTitle title;
    boolean chose;
    @Override
    protected void initVariables(Intent intent) {
        chose = intent.getBooleanExtra("chose", false);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_friend_found);
    }

    @Override
    protected void LoadData() {
        initTitle();
    }

    private void initTitle() {
        NetRequest.getFormRequest(UrlManager.FRIEND_ALL, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {

                title = GsonUtil.GsonToBean(result, FriendAllTitle.class);
                for (int i = 0;i<title.getData().getList().size();i++){
                    navigation.addTab(new LeftNavigation.Tab().setText(title.getData().getList().get(i)));
                }
                navigation.setCurrentItem(0);
                initNet();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
        navigation.setOnTabChechListener(new LeftNavigation.OnTabCheckListener() {
            @Override
            public void onTabSelected(View v, int position) {
                initNet();
            }
        });
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",title.getData().getList().get(navigation.getPosition()));
        NetRequest.postFormRequest(UrlManager.FRIEND_ALL, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                FriendAllData data = GsonUtil.GsonToBean(result, FriendAllData.class);
                initRc(data.getData().getList());
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initRc(List<FriendAllData.DataBean.ListBeanX> list) {
        rc.setLayoutManager(new LinearLayoutManager(this));
        FriendFoundRcAdapter adapter = new FriendFoundRcAdapter(R.layout.friend_found_item,list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if(chose){
                    Intent intent = new Intent();
                    intent.putExtra("cid",list.get(i).getCid());
                    intent.putExtra("title",list.get(i).getTitle());
                    setResult(300,intent);
                    finish();
                }else {
                    Intent intent = new Intent(FriendFoundActivity.this, FriendDetailActivity.class);
                    intent.putExtra("id",list.get(i).getCid());
                    startActivity(intent);
                }
            }
        });
        rc.setAdapter(adapter);
    }
}
