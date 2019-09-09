package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.NewVideo;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By MR.D
 * 2019/8/15
 * USE:
 **/
public class RcAdapterVideo2 extends BaseQuickAdapter<NewVideo.DataBean.ListBean,BaseViewHolder> {

    public RcAdapterVideo2(int layoutResId, @Nullable List<NewVideo.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewVideo.DataBean.ListBean s) {
//        MyGridView grid2 = helper.getView(R.id.RecyclerGrid);
//        grid2.setAdapter(new Grid2Adapter(mContext,s.getList()));
//        helper.setText(R.id.HomeListTitle,s.getTitle());
        Glide.with(mContext).load(s.getImage()).error(R.drawable.fq_ic_placeholder).into((ImageView) helper.getView(R.id.typeImage));
        helper.setText(R.id.name,s.getTitle());
        helper.setText(R.id.time,s.getPlaytime());
    }

}
