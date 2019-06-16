package com.mymusic.music.View.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.VideoData;
import com.mymusic.music.R;
import com.mymusic.music.Util.Love;
import com.mymusic.music.Util.MyVideoPlayer;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
import com.mymusic.music.base.BaseRecAdapter;
import com.mymusic.music.base.BaseRecViewHolder;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Create By MR.D
 * 2019/5/30
 * USE:视频页Rc适配器
 **/

public class VideoRecyclerViewAdapter extends BaseRecAdapter<VideoData.DataBean.ListBean, VideoViewHolder> {
    public Context context;
    List<VideoData.DataBean.ListBean> list ;
    public static final int CURRENT_STATE_PAUSE = 5;
    private long pressedTime = 0;
    public VideoRecyclerViewAdapter(Context context, List<VideoData.DataBean.ListBean> list) {
        super(list);
        this.context = context;
        this.list = list;
    }

    @Override
    public void onHolder(VideoViewHolder holder, VideoData.DataBean.ListBean bean, int position) {
        //设置视频大小
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        holder.mp_video.setUp(bean.getFilepath(), JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
        holder.video_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.video_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.video_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
        holder.video_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity(UserDetailActivity.class);
            }
        });
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                long normalTime = System.currentTimeMillis();
//                if(normalTime - pressedTime < 500){
//                    Toast.makeText(context,"爱心",Toast.LENGTH_SHORT).show();
//                    return;
//                }else{
//                    pressedTime = normalTime;
//                }
                if(holder.mp_video.isPlay()){
                    holder.mp_video.goOnPlayOnPause();
                } else {//暂停
                    if (holder.mp_video.currentState == JZVideoPlayer.CURRENT_STATE_PAUSE) {
                        holder.mp_video.goOnPlayOnResume();
                    } else {
                        holder.mp_video.startVideo();
                    }
                }
            }
        });
        if (position == 0) {
            holder.mp_video.startVideo();
        }
//        Glide.with(context).load(bean).into(holder.mp_video.thumbImageView);
        Glide.with(context).load(bean.getAvatar_thumb()).into(holder.video_head);
        holder.title.setText(bean.getSharecontent());
        holder.des.setText(bean.getUser_nicename());
    }



    @Override
    public VideoViewHolder onCreateHolder() {
        return new VideoViewHolder(getViewByRes(R.layout.video_item_layout));
    }
    public void goActivity(Class<?> clazz){
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    void showBottomSheetDialog(){
        BottomSheetDialog bottomSheet = new BottomSheetDialog(context);//实例化
        bottomSheet.setCancelable(true);//设置点击外部是否可以取消
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_comment_layout, null);
        bottomSheet.setContentView(view);//设置对框框中的布局
        bottomSheet.show();//显示弹窗
    }

    private void CopyText(String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(null, text);
        clipboard.setPrimaryClip(clipData);
    }
}

