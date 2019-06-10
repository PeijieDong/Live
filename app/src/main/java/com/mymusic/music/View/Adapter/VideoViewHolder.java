package com.mymusic.music.View.Adapter;

import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.mymusic.music.R;
import com.mymusic.music.Util.MyVideoPlayer;
import com.mymusic.music.base.BaseRecViewHolder;

public class VideoViewHolder extends BaseRecViewHolder {
    public View rootView;
    public MyVideoPlayer mp_video;
    public ConstraintLayout container;


    public VideoViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        this.mp_video = rootView.findViewById(R.id.mp_video);
        container = rootView.findViewById(R.id.video_container);
    }

}
