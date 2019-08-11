package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.VideoFind;
import com.mymusic.music.DataBean.VideoItem;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/4 21:19
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FindRcAdapter extends BaseQuickAdapter<VideoFind.DataBean.ListBean,BaseViewHolder> {

    public FindRcAdapter(int layoutResId, @Nullable List<VideoFind.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, VideoFind.DataBean.ListBean s) {
        Glide.with(mContext).load(s.getImage()).into((ImageView) baseViewHolder.getView(R.id.image));
        baseViewHolder.setText(R.id.title,s.getTitle())
        .setText(R.id.playNum,s.getClick()+"次播放");
//        .setText(R.id.score,s.get);
    }
}
