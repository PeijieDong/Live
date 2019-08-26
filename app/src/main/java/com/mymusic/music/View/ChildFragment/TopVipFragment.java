package com.mymusic.music.View.ChildFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.Vip;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.TopUpRcAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Request;

public class TopVipFragment extends BaseFragment {

    @BindView(R.id.rc)
    RecyclerView rc;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.top_vip_fragment,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        initNet();

    }

    private void initNet() {
        loading();
        NetRequest.postFormRequest(UrlManager.TOPUP_VIP, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                hideloading();
                Vip bean = GsonUtil.GsonToBean(result, Vip.class);
                rc.setLayoutManager(new LinearLayoutManager(getContext()));
                TopUpRcAdapter adapter = new TopUpRcAdapter(R.layout.vip_topup_layout, bean.getList());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.foot_vip_layout, null);
                adapter.addFooterView(view);
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()){
                            case R.id.doit:
                                if(Live.getInstance().getToken(getContext()).equals("")){
                                    Intent intent = new Intent(getContext(), LoginActivity.class);
                                    startActivity(intent);
                                    return;
                                }
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                Uri uri = Uri.parse(bean.getList().get(position).getRechargeURL());
                                intent.setData(uri);
                                startActivity(intent);
                                break;
                        }
                    }
                });
                rc.setAdapter(adapter);
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
}
