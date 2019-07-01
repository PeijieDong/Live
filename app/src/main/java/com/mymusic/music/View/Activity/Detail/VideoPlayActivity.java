package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mymusic.music.DataBean.VideoData;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.VideoRcAdapter;
import com.mymusic.music.View.Adapter.VideoRecyclerViewAdapter;
import com.mymusic.music.View.Adapter.VideoViewHolder;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import okhttp3.Request;

public class VideoPlayActivity extends BaseActivity {


    @BindView(R.id.video_Rc)
    RecyclerView videoRc;
    private PagerSnapHelper helper;
    private LinearLayoutManager layoutManager;
    private RecyclerView.ViewHolder viewHolder;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_video_play);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        NetRequest.postFormRequest(UrlManager.Video_List, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                VideoData bean = GsonUtil.GsonToBean(result, VideoData.class);
                initView(bean);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initView(VideoData bean) {
        //Recycleview页面滑动
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(videoRc);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        videoRc.setLayoutManager(layoutManager);
        videoRc.setAdapter(new VideoRcAdapter(this,bean.getData().getList()));
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

}
