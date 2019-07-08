package com.mymusic.music.View.ChildFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/6/9 21:49
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendDetailFragment1 extends BaseFragment {

    @BindView(R.id.Rc)
    RecyclerView rc;
    private String id;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.friend_detail_fragment1,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        id = bundle.getString("id");
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("type","1");
        NetRequest.postFormRequest(UrlManager.FRIEND_DETAILS_LIST, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                HomeData data = GsonUtil.GsonToBean(result, HomeData.class);
                rc.setLayoutManager(new LinearLayoutManager(getContext()));
                HomePagerRecyclerViewAdapter adapter = new HomePagerRecyclerViewAdapter(data.getData().getList());
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(getContext(), DetailsActivity.class);
                        intent.putExtra("id",data.getData().getList().get(position).getId());
                        startActivity(intent);
                    }
                });
                rc.setAdapter(adapter);
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

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {

    }
}
