package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.PinDao;
import com.mymusic.music.R;
import com.mymusic.music.Util.MyGridView;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/4 21:37
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class MoreRcAdapter extends BaseQuickAdapter<PinDao.DataBean.ListBeanX,BaseViewHolder> {

    public MoreRcAdapter(int layoutResId, @Nullable List<PinDao.DataBean.ListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PinDao.DataBean.ListBeanX s) {
        MyGridView grid = helper.getView(R.id.grid);
        grid.setAdapter(new GridAdapter(mContext,s.getList()));
        helper.setText(R.id.title,s.getTitle());
    }
}
