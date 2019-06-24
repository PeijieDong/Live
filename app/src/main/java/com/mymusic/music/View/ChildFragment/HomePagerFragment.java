package com.mymusic.music.View.ChildFragment;

import android.content.Intent;
import android.content.UriMatcher;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.R;
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
 * Create By mr.mao in 2019/5/29 22:19
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class HomePagerFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, OnRefreshListener {

    @BindView(R.id.home_pager_Rc)
    RecyclerView homePagerRecyclerview;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private int position;
    private List<HomeData.DataBean.ListBean> list;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_pager,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        refresh.autoRefresh();
    }

    private void initNet() {
        Bundle bundle = getArguments();
        position = bundle.getInt("position");
        HashMap<String, String> map = new HashMap<>();
        map.put("cate", position+1+"");
        NetRequest.getFormRequest(UrlManager.HOME_DATA, map, new NetRequest.DataCallBack() {
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
    ImageView like;
    TextView likeNum;
    private void initRc(List<HomeData.DataBean.ListBean> list) {
        this.list = list;
        homePagerRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        HomePagerRecyclerViewAdapter adapter = new HomePagerRecyclerViewAdapter(list);
        adapter.setOnItemClickListener(this);
//        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                like = view.findViewById(R.id.icon_like);
//                likeNum = view.findViewById(R.id.likeNum);
//                initLike(list.get(position));
//            }
//        });
        homePagerRecyclerview.setAdapter(adapter);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        refresh.setOnRefreshListener(this);
    }

    @Override
    protected void LoadData() {

    }

//    private void initLike(HomeData.DataBean.ListBean item) {
//        //点赞
//        HashMap<String, String> map = new HashMap<>();
//        map.put("type","1");
//        map.put("id",item.getId());
//        NetRequest.postFormRequest(UrlManager.Like, map, new NetRequest.DataCallBack() {
//            @Override
//            public void requestSuccess(String result) throws Exception {
//                Toast.makeText(getContext(),"点赞成功",Toast.LENGTH_SHORT).show();
//                like.setBackground(getContext().getResources().getDrawable(R.drawable.ic_launcher_background));
//                like.setClickable(false);
//                likeNum.setText(Integer.valueOf(likeNum.getText().toString())+1+"");
//            }
//
//            @Override
//            public void requestFailure(Request request, IOException e) {
//                Toast.makeText(getContext(),"点赞失败",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("id",list.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        initNet();
        refresh.finishRefresh();
    }
}

