package com.mymusic.music.View.ChildFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.FriendFindData;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.FriendDetailActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.FriendFindRecyclerviewAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/6/1 16:39
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendFindFragment extends BaseFragment implements OnRefreshListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.refresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.friendFindRc)
    RecyclerView recyclerView;
    private FriendFindData data;
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
                refreshLayout.finishRefresh();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initRc(FriendFindData data) {
        this.data = data;
        FriendFindRecyclerviewAdapter adapter =
                new FriendFindRecyclerviewAdapter(R.layout.fragment_friend_find_item, data.getData().getList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.find_item_focus:
                        TextView focus = view.findViewById(R.id.find_item_focus);
                        focus.setBackgroundResource(R.drawable.isfocus);
                        focus.setText("取消关注");
                        initFocusFriend(true,position);
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
        refreshLayout.finishRefresh();

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if(Live.getInstance().getUser(getContext()) == null){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            getActivity().startActivity(intent);
            return ;
        }
        Intent intent = new Intent(getContext(), FriendDetailActivity.class);
        String cid = data.getData().getList().get(position).getCid();
        intent.putExtra("id",cid);
        startActivity(intent);
    }

    private void initFocusFriend(boolean isFocus, int i) {
        String url = "";
        if(isFocus){
            url = UrlManager.Focus_Friend;
        }else{
            url = UrlManager.NoFocus_Friend;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("touid",data.getData().getList().get(i).getCid());
        if(Live.getInstance().getToken(getContext()) == null){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(url, map, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                Toast.makeText(getContext(),"操作成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(getContext(),"操作失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
