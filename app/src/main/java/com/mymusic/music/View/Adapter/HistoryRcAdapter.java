package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.History;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/7 10:17
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class HistoryRcAdapter extends BaseQuickAdapter<History.DataBean.ListBean,BaseViewHolder> {

    public HistoryRcAdapter(int layoutResId, @Nullable List<History.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, History.DataBean.ListBean item) {
        helper.setText(R.id.history_rc_time,item.getCreatetime())
                .setText(R.id.history_rc_title,"["+item.getTitle()+"]");
        if(item.getTitle().equals("视频")){
            helper.setVisible(R.id.play_icon,true);
        }
        Glide.with(mContext).load(item.getImage()).error(R.drawable.fq_ic_placeholder).into((ImageView) helper.getView(R.id.image));
    }
}
