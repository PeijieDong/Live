package com.mymusic.music.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mymusic.music.DataBean.VideoData;
import com.mymusic.music.Live;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.VideoRecyclerViewAdapter;
import com.mymusic.music.View.Adapter.VideoViewHolder;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.R;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import okhttp3.Request;

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
    private VideoRecyclerViewAdapter adapter;
    private VideoData bean;
    private int select = 0;
    long mLastTime=0;
    long mCurTime=0;
    public VideoViewHolder selectHolder;
    public int selectPosition;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    View view = helper.findSnapView(layoutManager);
                    viewHolder = videoRc.getChildViewHolder(view);
                    if(((VideoViewHolder) viewHolder)!=null && ((VideoViewHolder) viewHolder).mp_video.isPlay()){
                        ((VideoViewHolder) viewHolder).mp_video.goOnPlayOnPause();
                    } else {//暂停
                        if (((VideoViewHolder) viewHolder).mp_video.currentState == JZVideoPlayer.CURRENT_STATE_PAUSE) {
                            ((VideoViewHolder) viewHolder).mp_video.goOnPlayOnResume();
                        } else {
                            ((VideoViewHolder) viewHolder).mp_video.startVideo();
                        }
                    }

                    break;
                case 2:
                    if(Live.getInstance().getUser(getContext()) == null){
                        Intent intent1 = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent1);
                        return;
                    }
                    initLike(selectHolder);
                    break;
            }
        }
    };


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
        initNet();
    }

    private void initNet() {
        NetRequest.postFormRequest(UrlManager.Video_List, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                bean = GsonUtil.GsonToBean(result, VideoData.class);
                initView(bean);
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

    private void initView(VideoData bean) {
        //Recycleview页面滑动
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(videoRc);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        videoRc.setLayoutManager(layoutManager);
        adapter = new VideoRecyclerViewAdapter(getContext(), bean.getData().getList());
        adapter.setListener(new VideoRecyclerViewAdapter.VideoListener() {
            @Override
            public void click(View v, int position,VideoViewHolder holder) {
                selectPosition = position;
                selectHolder = holder;
                select = position;
                mLastTime=mCurTime;
                mCurTime= System.currentTimeMillis();
                if(mCurTime-mLastTime<300){//双击事件
                    mCurTime =0;
                    mLastTime = 0;
                    handler.removeMessages(1);
                    handler.sendEmptyMessage(2);
                }else{//单击事件
                    handler.sendEmptyMessageDelayed(1, 310);
                }
            }
        });
        videoRc.setAdapter(adapter);
        videoRc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState){
                    //停止滚动
                    case RecyclerView.SCROLL_STATE_IDLE:
                        View view = helper.findSnapView(layoutManager);
                        JZVideoPlayer.releaseAllVideos();
                        viewHolder = recyclerView.getChildViewHolder(view);
                        int position = viewHolder.getAdapterPosition();
                        //播放视频
                        ((VideoViewHolder) viewHolder).mp_video.startVideo();
                        initPlay(bean.getData().getList().get(position).getVid());

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
        map.put("id",position);
        NetRequest.postFormHeadRequest(UrlManager.Play_Num, map, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
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

    //    private void initCollection() {
//        if(Live.getInstance().getToken(getContext()) == null){
//            Intent intent = new Intent(getContext(), LoginActivity.class);
//            getContext().startActivity(intent);
//            return;
//        }
//        HashMap<String, String> map = new HashMap<>();
//        map.put("id",bean.getData().getList().get(select).getVid());
//        NetRequest.postFormHeadRequest(UrlManager.Vide_Collection, map, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
//            @Override
//            public void requestSuccess(String result) throws Exception {
//                Log.e("33",result);
//                Toast.makeText(getContext(),"收藏成功",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void requestFailure(Request request, IOException e) {
//
//            }
//            @Override
//            public void TokenFail() {
//                LoginDialog dialog = new LoginDialog(getActivity());
//                dialog.Show();
//            }
//        });
//    }
    private void initLike(VideoViewHolder holder) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type","1");
        map.put("id",bean.getData().getList().get(selectPosition).getVid());
        NetRequest.postFormRequest(UrlManager.Video_Zan, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(getContext(),"点赞成功",Toast.LENGTH_SHORT).show();
                holder.likeNum.setText(Integer.parseInt(holder.likeNum.getText().toString())+1+"");
                holder.video_like.setImageResource(R.drawable.yp_video_like);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getContext());
                dialog.Show();
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
