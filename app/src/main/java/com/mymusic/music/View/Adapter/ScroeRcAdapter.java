package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.Scroe;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/7/11 0:01
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class ScroeRcAdapter extends BaseQuickAdapter<Scroe.DataBean.ListBean,BaseViewHolder> {

    public ScroeRcAdapter(int layoutResId, @Nullable List<Scroe.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Scroe.DataBean.ListBean item) {
        helper.setText(R.id.title,item.getType())
                .setText(R.id.time,item.getAddtime())
                .setText(R.id.scroe,item.getScore())
                .setText(R.id.type,"积分");
    }
}
