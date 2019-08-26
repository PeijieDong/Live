package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mymusic.music.DataBean.FriendDetail;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.DataBean.Play;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.AppUtil;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MytaskActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MywalletActivity;
import com.mymusic.music.View.Adapter.VideoRc2Adapter;
import com.mymusic.music.View.Adapter.VideoRc3Adapter;
import com.mymusic.music.View.Adapter.VideoViewHolder;
import com.mymusic.music.View.Adapter.VideoViewHolder2;

import com.mymusic.music.View.Adapter.VideoViewHolder3;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
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
    private VideoViewHolder3 viewHolder3;
    private VideoViewHolder2 viewHolder2;
    @Override
    protected void initVariables(Intent intent) {
        userVideo = (List<FriendDetail.DataBean.ListBean>) intent.getSerializableExtra("userVideo");
        playData = (List<HomeData.DataBean.ListBean.ObjsBean>) intent.getSerializableExtra("playData");
        data = (FriendDetail) intent.getSerializableExtra("video");
        if(data != null && data.getData() != null){
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
            adapter = new VideoRc2Adapter(this, userVideo,position);
            videoRc.setAdapter(adapter);
            initPlay(viewHolder2);
            adapter.setListener(new VideoRc2Adapter.ViewHolderListener() {
                @Override
                public void backViewHolder(VideoViewHolder2 holder) {
                    holder.mp_video.startVideo();
                }

                @Override
                public void holder(VideoViewHolder2 holder) {
                    viewHolder = holder;
                    initPlay(holder);
                    holder.mp_video.setId(userVideo.get(position).getId());
                }
            });
        }
        if(playData != null){
            adapter3 = new VideoRc3Adapter(this, playData,position);
            videoRc.setAdapter(adapter3);
            initPlay2(viewHolder3);
            adapter3.setListener(new VideoRc3Adapter.ViewHolderListener() {
                @Override
                public void backViewHolder(VideoViewHolder3 holder) {
                    holder.mp_video.startVideo();
                }
                @Override
                public void holder(VideoViewHolder3 holder) {
                    viewHolder = holder;
                    initPlay2(holder);
                    holder.mp_video.setId(playData.get(position).getVid());
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
                            initPlay((VideoViewHolder2)viewHolder);
                        }
                        if(playData != null){
                            ((VideoViewHolder3) viewHolder).mp_video.startVideo();
                            initPlay2((VideoViewHolder3) viewHolder);
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


    private void initPlay2(VideoViewHolder3 viewHolder) {
        HashMap<String, String> map = new HashMap<>();
        map.put("client", AppUtil.getSerialNumber());
        loading();
        NetRequest.postFormHeadRequest(UrlManager.Play_Num, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) {
                Log.e("3333",result);
                Play bean = GsonUtil.GsonToBean(result, Play.class);
                if(bean.getData().getList().getCount() <= 0){
                    JZVideoPlayer.releaseAllVideos();
                    JZVideoPlayer.goOnPlayOnPause();
                    viewHolder.noNum.setVisibility(View.VISIBLE);
                    viewHolder.mp_video.setVisibility(View.GONE);
                    viewHolder.noMoneyTitle.setText("免费观看已用完，消耗积分/番茄币享今日无限观看\n当前积分"+bean.getData().getList().getScore()
                            +"个，番茄币"+bean.getData().getList().getMoney()+"个");
                    if(Integer.parseInt(bean.getData().getList().getMoney()) < 10){
                        viewHolder.goMoney.setText("充值番茄币");
                        viewHolder.goMoney.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(Live.getInstance().getToken(VideoPlayActivity.this).equals("")){
                                    Intent intent = new Intent(VideoPlayActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }else {
                                    Intent intent = new Intent(VideoPlayActivity.this, MywalletActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
                    }else{
                        viewHolder.goMoney.setText("10币观看");
                        viewHolder.goMoney.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                initLook2("1",viewHolder);
                            }
                        });
                    }
                    if(Integer.parseInt(bean.getData().getList().getScore()) < 10){
                        viewHolder.goLook.setText("赚取积分");
                        viewHolder.goLook.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(Live.getInstance().getToken(VideoPlayActivity.this).equals("")){
                                    Intent intent = new Intent(VideoPlayActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }else {
                                    Intent intent = new Intent(VideoPlayActivity.this, MytaskActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
                    }else{
                        viewHolder.goLook.setText("10积分观看");
                        viewHolder.goLook.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                initLook2("0",viewHolder);
                            }
                        });
                    }
                }
            }
            @Override
            public void requestFailure(Request request, IOException e) {
            }
            @Override
            public void TokenFail() {
            }
        });
        hideloading();
    }

    private void initLook2(String type, VideoViewHolder3 viewHolder) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type",type);
        loading();
        NetRequest.postFormHeadRequest(UrlManager.GoMoney, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) {
                Log.e("3333",result);
                viewHolder.mp_video.startVideo();
                viewHolder.mp_video.setVisibility(View.VISIBLE);
                viewHolder.noNum.setVisibility(View.GONE);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(VideoPlayActivity.this,"请求失败",Toast.LENGTH_SHORT);
            }

            @Override
            public void TokenFail() {
                Intent intent = new Intent(VideoPlayActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        hideloading();
    }

    private void initPlay(VideoViewHolder2 viewHolder) {
        HashMap<String, String> map = new HashMap<>();
        map.put("client", AppUtil.getSerialNumber());
        loading();
        NetRequest.postFormHeadRequest(UrlManager.Play_Num, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) {
                Log.e("3333",result);
                Play bean = GsonUtil.GsonToBean(result, Play.class);
                if(bean.getData().getList().getCount() == 0){
                    JZVideoPlayer.releaseAllVideos();
                    JZVideoPlayer.goOnPlayOnPause();
                    viewHolder.mp_video.goOnPlayOnPause();
                    viewHolder.noNum.setVisibility(View.VISIBLE);
                    viewHolder.mp_video.setVisibility(View.GONE);
                    viewHolder.noMoneyTitle.setText("免费观看已用完，消耗积分/番茄币享今日无限观看当前积分"+bean.getData().getList().getScore()
                            +"个，番茄币"+bean.getData().getList().getMoney()+"个");
                    if(Integer.parseInt(bean.getData().getList().getMoney()) < 10){
                        viewHolder.goMoney.setText("充值番茄币");
                        viewHolder.goMoney.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(Live.getInstance().getToken(VideoPlayActivity.this).equals("")){
                                    Intent intent = new Intent(VideoPlayActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }else {
                                    Intent intent = new Intent(VideoPlayActivity.this, MywalletActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
                    }else{
                        viewHolder.goMoney.setText("10币观看");
                        viewHolder.goMoney.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                initLook("1",viewHolder);
                            }
                        });
                    }
                    if(Integer.parseInt(bean.getData().getList().getScore()) < 10){
                        viewHolder.goLook.setText("赚取积分");
                        viewHolder.goLook.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(Live.getInstance().getToken(VideoPlayActivity.this).equals("")){
                                    Intent intent = new Intent(VideoPlayActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }else {
                                    Intent intent = new Intent(VideoPlayActivity.this, MytaskActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
                    }else{
                        viewHolder.goLook.setText("10积分观看");
                        viewHolder.goLook.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                initLook("0",viewHolder);
                            }
                        });
                    }
                }
            }
            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(VideoPlayActivity.this,"请求失败",Toast.LENGTH_SHORT);
            }
            @Override
            public void TokenFail() {
                Intent intent = new Intent(VideoPlayActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        hideloading();
    }

    private void initLook(String type,VideoViewHolder2 viewHolder) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type",type);
        loading();
        NetRequest.postFormHeadRequest(UrlManager.GoMoney, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) {
                Log.e("3333",result);
                viewHolder.mp_video.startVideo();
                viewHolder.mp_video.setVisibility(View.VISIBLE);
                viewHolder.noNum.setVisibility(View.GONE);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(VideoPlayActivity.this,"请求失败",Toast.LENGTH_SHORT);
            }

            @Override
            public void TokenFail() {
                Intent intent = new Intent(VideoPlayActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        hideloading();
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
