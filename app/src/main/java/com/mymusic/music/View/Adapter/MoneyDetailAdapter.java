package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.MoneyDetail;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/7/4 21:44
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class MoneyDetailAdapter extends BaseQuickAdapter <MoneyDetail.DataBean.ListBean,BaseViewHolder> {

    public MoneyDetailAdapter(int layoutResId, @Nullable List<MoneyDetail.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MoneyDetail.DataBean.ListBean item) {
        helper.setText(R.id.title,item.getContent())
                .setText(R.id.time,item.getCreatetime())
                .setText(R.id.detail,item.getNum()+"币");
    }
}
