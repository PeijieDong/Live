package com.mymusic.music.View.Adapter;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;

import com.mymusic.music.R;
import com.mymusic.music.Util.MyVideoPlayer;
import com.mymusic.music.base.BaseRecViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideoViewHolder extends BaseRecViewHolder {
    public View rootView;
    public MyVideoPlayer mp_video;
    public CircleImageView video_head;
    public ImageView video_share;
    public ImageView video_like;
    public ImageView video_comment;
    public ConstraintLayout container;


    public VideoViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        this.mp_video = rootView.findViewById(R.id.mp_video);
        this.video_head = rootView.findViewById(R.id.video_head);
        this.video_like = rootView.findViewById(R.id.video_like);
        this.video_share = rootView.findViewById(R.id.video_share);
        this.video_comment = rootView.findViewById(R.id.video_comment);
        container = rootView.findViewById(R.id.video_container);
    }

}
