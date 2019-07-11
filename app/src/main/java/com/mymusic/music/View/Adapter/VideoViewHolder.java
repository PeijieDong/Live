package com.mymusic.music.View.Adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    public TextView title;
    public TextView des;
    public ImageView putVideo;
    public TextView likeNum;
    public TextView shareNum;
    public TextView commentNum;
    public ImageView focus;
    public ConstraintLayout noNum;
    public TextView goLook;
    public TextView goMoney;
    public TextView noMoneyTitle;



    public VideoViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        mp_video = rootView.findViewById(R.id.mp_video);
        video_head = rootView.findViewById(R.id.video_head);
        video_like = rootView.findViewById(R.id.video_like);
        video_share = rootView.findViewById(R.id.video_share);
        video_comment = rootView.findViewById(R.id.video_comment);
        container = rootView.findViewById(R.id.video_container);
        title = rootView.findViewById(R.id.video_title);
        des = rootView.findViewById(R.id.video_des);
        putVideo = rootView.findViewById(R.id.put_video);
        likeNum = rootView.findViewById(R.id.likeNum);
        shareNum = rootView.findViewById(R.id.shareNum);
        commentNum = rootView.findViewById(R.id.commentNum);
        focus = rootView.findViewById(R.id.focus_bt);

        noNum = rootView.findViewById(R.id.no_num);
        goLook = rootView.findViewById(R.id.goLook);
        goMoney = rootView.findViewById(R.id.goMoney);
        noMoneyTitle = rootView.findViewById(R.id.noMoneyTitle);

    }

}
