package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.FeedBack;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/7/11 20:24
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FeedBackRcAdapter extends BaseQuickAdapter<FeedBack.DataBean.ListBean,BaseViewHolder> {

    public FeedBackRcAdapter(int layoutResId, @Nullable List<FeedBack.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedBack.DataBean.ListBean item) {
        helper.setText(R.id.content,item.getContent())
                .setText(R.id.time,item.getAddtime());
    }
}
