package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.VideoItem;
import com.mymusic.music.R;
import com.mymusic.music.Util.MyGridView;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/4 19:26
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class RcAdpaterVideo extends BaseQuickAdapter <VideoItem.DataBean.ListBeanX,BaseViewHolder> {

    public RcAdpaterVideo(int layoutResId, @Nullable List<VideoItem.DataBean.ListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoItem.DataBean.ListBeanX s) {
        helper.addOnClickListener(R.id.more);
        MyGridView grid2 = helper.getView(R.id.RecyclerGrid);
        grid2.setAdapter(new Grid2Adapter(mContext,s.getList()));
        helper.setText(R.id.HomeListTitle,s.getTitle());
    }
}
