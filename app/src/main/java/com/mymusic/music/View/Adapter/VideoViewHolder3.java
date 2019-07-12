package com.mymusic.music.View.Adapter;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mymusic.music.R;
import com.mymusic.music.Util.MyVideoPlayer;
import com.mymusic.music.base.BaseRecViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/7/3 21:46
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoViewHolder3 extends BaseRecViewHolder {
    public View rootView;
    public MyVideoPlayer mp_video;
    public CircleImageView video_head;
    public ConstraintLayout container;
    public TextView title;
    public TextView des;
    public TextView focus;
    public ImageView more;
    public ConstraintLayout noNum;
    public TextView goLook;
    public TextView goMoney;
    public TextView noMoneyTitle;



    public VideoViewHolder3(View rootView) {
        super(rootView);
        this.rootView = rootView;
        mp_video = rootView.findViewById(R.id.mp_video);
        video_head = rootView.findViewById(R.id.head);
        container = rootView.findViewById(R.id.video_container);
        title = rootView.findViewById(R.id.video_title);
        des = rootView.findViewById(R.id.video_des);
        focus = rootView.findViewById(R.id.focus);
        more = rootView.findViewById(R.id.more);

        noNum = rootView.findViewById(R.id.no_num);
        goLook = rootView.findViewById(R.id.goLook);
        goMoney = rootView.findViewById(R.id.goMoney);
        noMoneyTitle = rootView.findViewById(R.id.noMoneyTitle);
    }

}

