package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.CommentBean;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/23 0:26
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoFragmentRcAdapter extends BaseQuickAdapter<CommentBean.DataBean.ListBean,BaseViewHolder> {
    public VideoFragmentRcAdapter(int layoutResId, @Nullable List<CommentBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean.DataBean.ListBean item) {

    }
}
