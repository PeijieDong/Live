package com.mymusic.music.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
import com.mymusic.music.View.Adapter.VideoRecyclerViewAdapter;
import com.mymusic.music.View.Adapter.VideoViewHolder;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;

/**
 * Create By mr.mao in 2019/5/29 21:30
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoFragment extends BaseFragment {

    @BindView(R.id.video_Rc)
    RecyclerView videoRc;
    private PagerSnapHelper helper;
    private LinearLayoutManager layoutManager;
    private RecyclerView.ViewHolder viewHolder;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_video,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        List<String> list = new ArrayList<>();
        list.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201811/26/09/5bfb4c55633c9.mp4");
        list.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201805/100651/201805181532123423.mp4");
        list.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803151735198462.mp4");
        list.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803150923220770.mp4");
        list.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803150922255785.mp4");
        list.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803150920130302.mp4");
        list.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803141625005241.mp4");
        list.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803141624378522.mp4");
        list.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803131546119319.mp4");

        //Recycleview页面滑动
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(videoRc);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        videoRc.setLayoutManager(layoutManager);
        videoRc.setAdapter(new VideoRecyclerViewAdapter(getContext(),list));
        videoRc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState){
                    //停止滚动
                    case RecyclerView.SCROLL_STATE_IDLE:
                        View view = helper.findSnapView(layoutManager);
                        JZVideoPlayer.releaseAllVideos();
                        viewHolder = recyclerView.getChildViewHolder(view);
                        //播放视频
                        ((VideoViewHolder) viewHolder).mp_video.startVideo();

                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        break;

                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
    //fragment显示与隐藏调用方法
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            JZVideoPlayer.releaseAllVideos();
        }else{
            if(viewHolder != null){
                ((VideoViewHolder) viewHolder).mp_video.startVideo();
            }
        }
    }
}
