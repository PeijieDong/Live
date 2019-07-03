package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mymusic.music.DataBean.FriendDetail;
import com.mymusic.music.DataBean.VideoData;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.VideoRc2Adapter;
import com.mymusic.music.View.Adapter.VideoRcAdapter;
import com.mymusic.music.View.Adapter.VideoRecyclerViewAdapter;
import com.mymusic.music.View.Adapter.VideoViewHolder;
import com.mymusic.music.View.Adapter.VideoViewHolder2;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.io.Serializable;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import okhttp3.Request;

public class VideoPlayActivity extends BaseActivity {


    @BindView(R.id.video_Rc)
    RecyclerView videoRc;
    private PagerSnapHelper helper;
    private LinearLayoutManager layoutManager;
    private RecyclerView.ViewHolder viewHolder;
    private FriendDetail data;
    private int position;

    @Override
    protected void initVariables(Intent intent) {
        data = (FriendDetail) intent.getSerializableExtra("video");
        position = intent.getIntExtra("position", 0);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_video_play);
    }

    @Override
    protected void LoadData() {
        initView();
    }


    private void initView() {
        //Recycleview页面滑动
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(videoRc);
        layoutManager = new LinearLayoutManager(this);
        videoRc.setLayoutManager(layoutManager);
        VideoRc2Adapter adapter = new VideoRc2Adapter(this, data.getData().getList());
        videoRc.setAdapter(adapter);
        videoRc.scrollToPosition(position);
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
                        ((VideoViewHolder2) viewHolder).mp_video.startVideo();
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
        adapter.setListener(new VideoRc2Adapter.ViewHolderListener() {
            @Override
            public void backViewHolder(VideoViewHolder2 holder) {
                holder.mp_video.startVideo();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
