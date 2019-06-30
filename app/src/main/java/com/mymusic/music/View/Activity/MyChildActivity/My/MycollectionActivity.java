package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

public class MycollectionActivity extends BaseActivity {

    @BindView(R.id.Rc)
    RecyclerView rc;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mycollection);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("page","1");
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(UrlManager.Collection, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                HomeData data = GsonUtil.GsonToBean(result, HomeData.class);
                initRc(data.getData().getList());
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initRc(List<HomeData.DataBean.ListBean> list) {
        rc.setLayoutManager(new LinearLayoutManager(this));
        HomePagerRecyclerViewAdapter adapter = new HomePagerRecyclerViewAdapter(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MycollectionActivity.this, DetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
        rc.setAdapter(adapter);
    }
}
