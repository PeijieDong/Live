package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.Vip;
import com.mymusic.music.R;

import java.util.List;

public class TopUpRcAdapter extends BaseQuickAdapter<Vip.ListBean,BaseViewHolder> {

    public TopUpRcAdapter(int layoutResId, @Nullable List<Vip.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Vip.ListBean item) {
        helper.addOnClickListener(R.id.doit);
        helper.setText(R.id.title2,item.getTitle()).setText(R.id.title3,item.getActivityValue())
                .setText(R.id.title4,item.getActivityDesc()).setText(R.id.title5,item.getPrice());
    }
}
