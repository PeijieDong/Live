package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.NoticeMess;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/7/13 15:37
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class NoticeRcAdapter extends BaseQuickAdapter<NoticeMess.DataBean.ListBean,BaseViewHolder> {

    public NoticeRcAdapter(int layoutResId, @Nullable List<NoticeMess.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeMess.DataBean.ListBean item) {
        helper.setText(R.id.title,item.getTitle())
                .setText(R.id.content,item.getContent())
                .setText(R.id.time,item.getCreatetime());
    }
}
