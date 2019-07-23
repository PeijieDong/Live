package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.DataBean.Like;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class MylikeActivity extends BaseActivity {

    @BindView(R.id.like_Rc)
    RecyclerView rc;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mylike);
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
        HashMap<String, String> map = new HashMap<>();
        map.put("token",Live.getInstance().getToken(this));
        map.put("page","1");
        NetRequest.postFormHeadRequest(UrlManager.MyLike, map,Live.getInstance().getToken(this) ,new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                HomeData bean = GsonUtil.GsonToBean(result, HomeData.class);
                initView(bean);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }

    private void initView(HomeData bean) {
        rc.setLayoutManager(new LinearLayoutManager(this));
        HomePagerRecyclerViewAdapter adapter = new HomePagerRecyclerViewAdapter(bean.getData().getList());
        adapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_layout,null));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MylikeActivity.this, DetailsActivity.class);
                intent.putExtra("id",bean.getData().getList().get(position).getId());
                startActivity(intent);
            }
        });
        rc.setAdapter(adapter);
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
