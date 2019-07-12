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
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.DataBean.UserVideo;
import com.mymusic.music.DataBean.VideoData;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.AppUtil;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.VideoRc2Adapter;
import com.mymusic.music.View.Adapter.VideoRc3Adapter;
import com.mymusic.music.View.Adapter.VideoRcAdapter;
import com.mymusic.music.View.Adapter.VideoRecyclerViewAdapter;
import com.mymusic.music.View.Adapter.VideoViewHolder;
import com.mymusic.music.View.Adapter.VideoViewHolder2;

import com.mymusic.music.View.Adapter.VideoViewHolder3;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

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
    private List<HomeData.DataBean.ListBean.ObjsBean> playData;
    private List<FriendDetail.DataBean.ListBean> userVideo;

    @Override
    protected void initVariables(Intent intent) {
        userVideo = (List<FriendDetail.DataBean.ListBean>) intent.getSerializableExtra("userVideo");
        playData = (List<HomeData.DataBean.ListBean.ObjsBean>) intent.getSerializableExtra("playData");
        data = (FriendDetail) intent.getSerializableExtra("video");
        if(data != null){
            userVideo = data.getData().getList();
        }
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
        VideoRc2Adapter adapter = null;
        VideoRc3Adapter adapter3 = null;
        if(userVideo != null){
            adapter = new VideoRc2Adapter(this, userVideo);
            videoRc.setAdapter(adapter);
            adapter.setListener(new VideoRc2Adapter.ViewHolderListener() {
                @Override
                public void backViewHolder(VideoViewHolder2 holder) {
                    holder.mp_video.startVideo();
                }
            });
        }
        if(playData != null){
            adapter3 = new VideoRc3Adapter(this, playData);
            videoRc.setAdapter(adapter3);
            adapter3.setListener(new VideoRc3Adapter.ViewHolderListener() {
                @Override
                public void backViewHolder(VideoViewHolder3 holder) {
                    holder.mp_video.startVideo();
                }
            });
        }
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
                        if(userVideo != null){
                            ((VideoViewHolder2) viewHolder).mp_video.startVideo();
                            initPlay(userVideo.get(position).getId());
                        }
                        if(playData != null){
                            ((VideoViewHolder3) viewHolder).mp_video.startVideo();
                            initPlay(playData.get(position).getVid());
                        }
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
    private void initPlay(String position) {
        HashMap<String, String> map = new HashMap<>();
        map.put("client", AppUtil.getSerialNumber());
        NetRequest.postFormHeadRequest(UrlManager.Play_Num, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
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
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
