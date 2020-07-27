package com.mymusic.music.View.ChildFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mymusic.music.DataBean.VideoItem;
import com.mymusic.music.R;
import com.mymusic.music.Util.GlideImageLoader;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LogUtils;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.VideoPindaoActivity;
import com.mymusic.music.View.Adapter.RcAdpaterVideo;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/7/31 13:17
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoItemFragment extends BaseFragment {

    @BindView(R.id.Rc)
    RecyclerView rc;
    @BindView(R.id.refresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.banner)
    Banner banner;
    String pid;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.item_video_fragment_layout,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        pid = bundle.getString("pid");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initRefresh();
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",pid);
        NetRequest.postFormRequest(UrlManager.GET_PINDAO_DETAIL, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                LogUtils.d("ds","返回数据"+ GsonUtil.GsonString(result));
                refreshLayout.finishRefresh();
                VideoItem bean = GsonUtil.GsonToBean(result, VideoItem.class);
                List<VideoItem.DataBean.AdBean> ad = bean.getData().getAd();
                List<String> images = new ArrayList<>();
                if(ad != null){
                    for (int i = 0;i<ad.size();i++){
                        images.add(ad.get(i).getImg());
                    }
                }
                banner.setImageLoader(new GlideImageLoader());
                banner.setImages(images);
                banner.start();
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.VIEW");
                        Uri uri = Uri.parse(bean.getData().getAd().get(position).getUrl());
                        intent2.setData(uri);
                        startActivity(intent2);
                    }
                });
                rc.setLayoutManager(new LinearLayoutManager(getContext()));
                RcAdpaterVideo videoAdapter = new RcAdpaterVideo(bean.getData().getList());
                videoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()){
                            case R.id.more:
                                Intent intent = new Intent(getContext() , VideoPindaoActivity.class);
                                intent.putExtra("id",bean.getData().getList().get(position).getPid());
                                intent.putExtra("title",bean.getData().getList().get(position).getTitle());
                                startActivity(intent);
                                break;
                            case R.id.adv_image:
                                Intent intent2 = new Intent();
                                intent2.setAction("android.intent.action.VIEW");
                                Uri uri = Uri.parse(bean.getData().getList().get(position).getLink());
                                intent2.setData(uri);
                                startActivity(intent2);
                                break;
                        }
                    }
                });
                rc.setAdapter(videoAdapter);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }

    @Override
    protected void LoadData() {

    }


    private void initRefresh() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initNet();
            }
        });
    }
}
