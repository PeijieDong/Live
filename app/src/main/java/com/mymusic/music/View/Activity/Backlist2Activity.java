package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.BlackList;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Adapter.BlackListRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class Backlist2Activity extends BaseActivity {

    @BindView(R.id.Rc)
    RecyclerView rc;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_backlist2);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        loading();
        NetRequest.postFormRequest(UrlManager.BLACK_LIST, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                BlackList bean = GsonUtil.GsonToBean(result, BlackList.class);
                rc.setLayoutManager(new LinearLayoutManager(Backlist2Activity.this));
                BlackListRcAdapter adapter = new BlackListRcAdapter(R.layout.black_list_item_layout, bean.getList());
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        initJieChu(bean.getList().get(i).getId());
                    }
                });
                rc.setAdapter(adapter);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(Backlist2Activity.this,"解除失败",1);
            }

            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
        hideloading();
    }

    private void initJieChu(String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("touid",id);
        NetRequest.postFormRequest(UrlManager.JIECHU, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                ToastUtil.show(Backlist2Activity.this,"解除成功",1);
                initNet();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(Backlist2Activity.this,"解除失败",1);
            }

            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
    @OnClick(R.id.back)
    public void ClickEvent(View view){
        finish();
    }
}
