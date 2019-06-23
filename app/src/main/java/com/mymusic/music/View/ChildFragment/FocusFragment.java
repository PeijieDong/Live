package com.mymusic.music.View.ChildFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.music.DataBean.Focus;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.FocusRcAdaper;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/6/2 20:41
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FocusFragment extends BaseFragment {

    @BindView(R.id.focus_Rc)
    RecyclerView focusRc;
    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_focus,container,false);
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
        NetRequest.postFormHeadRequest(UrlManager.Focus_List, null, Live.getInstance().getToken(getContext()),new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                Focus bean = GsonUtil.GsonToBean(result, Focus.class);
                focusRc.setLayoutManager(new LinearLayoutManager(getContext()));
                FocusRcAdaper adaper = new FocusRcAdaper(R.layout.focus_rc_layout, bean.getData().getList());
                adaper.setOnItemClickListener(this);
                focusRc.setAdapter(adaper);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }
}
