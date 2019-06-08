package com.mymusic.music.View.ChildFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.music.DataBean.FriendFindData;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.FriendFindRecyclerviewAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/6/1 16:39
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendFindFragment extends BaseFragment implements OnRefreshListener {
    @BindView(R.id.refresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.friendFindRc)
    RecyclerView recyclerView;
    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_friend_find_layout,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {


    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        initNet();
    }

    private void initNet() {
        NetRequest.postFormRequest(UrlManager.FRIEND_FIND, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                FriendFindData data = GsonUtil.GsonToBean(result, FriendFindData.class);
                initRc(data);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initRc(FriendFindData data) {
        FriendFindRecyclerviewAdapter adapter =
                new FriendFindRecyclerviewAdapter(R.layout.fragment_friend_find_item,data.getData().getList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        refreshLayout.finishRefresh();
    }
}
