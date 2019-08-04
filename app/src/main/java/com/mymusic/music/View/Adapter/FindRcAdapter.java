package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.VideoItem;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/4 21:19
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FindRcAdapter extends BaseQuickAdapter<VideoItem.DataBean.ListBeanX.ListBean,BaseViewHolder> {

    public FindRcAdapter(int layoutResId, @Nullable List<VideoItem.DataBean.ListBeanX.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, VideoItem.DataBean.ListBeanX.ListBean s) {
        Glide.with(mContext).load(s.getImg()).into((ImageView) baseViewHolder.getView(R.id.image));
    }
}
