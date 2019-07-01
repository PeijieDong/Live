package com.mymusic.music.View.Adapter;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mymusic.music.R;
import com.mymusic.music.Util.MyVideoPlayer;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/7/1 23:21
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
class VideoTwoViewHolder extends VideoViewHolder {
    public View rootView;
    public MyVideoPlayer mp_video;
    public TextView title;
    public TextView des;




    public VideoTwoViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        mp_video = rootView.findViewById(R.id.mp_video);
        title = rootView.findViewById(R.id.video_title);
        des = rootView.findViewById(R.id.video_des);

    }

}
