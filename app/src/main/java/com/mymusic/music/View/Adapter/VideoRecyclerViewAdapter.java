package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.R;
import com.mymusic.music.Util.MyVideoPlayer;
import com.mymusic.music.base.BaseRecAdapter;
import com.mymusic.music.base.BaseRecViewHolder;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Create By MR.D
 * 2019/5/30
 * USE:视频页Rc适配器
 **/

public class VideoRecyclerViewAdapter extends BaseRecAdapter<String, VideoViewHolder> {

    public VideoRecyclerViewAdapter(List<String> list) {
        super(list);
    }

    @Override
    public void onHolder(VideoViewHolder holder, String bean, int position) {
        //设置视频大小
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        holder.mp_video.setUp(bean, JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"sdsds",Toast.LENGTH_SHORT).show();
            }
        });
        if (position == 0) {
            holder.mp_video.startVideo();
        }
        Glide.with(context).load(bean).into(holder.mp_video.thumbImageView);

    }

    @Override
    public VideoViewHolder onCreateHolder() {
        return new VideoViewHolder(getViewByRes(R.layout.video_item_layout));
    }

}

