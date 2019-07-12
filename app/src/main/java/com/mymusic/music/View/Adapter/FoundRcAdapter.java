package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/7/7 18:36
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FoundRcAdapter extends BaseQuickAdapter<HomeData.DataBean.ListBean.ObjsBean,BaseViewHolder> {

    public FoundRcAdapter(int layoutResId, @Nullable List<HomeData.DataBean.ListBean.ObjsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeData.DataBean.ListBean.ObjsBean item) {
        Glide.with(mContext).load(item.getImageX()).error(R.drawable.gray_back).into((ImageView) helper.getView(R.id.video_icon));
        helper.setText(R.id.like_num,item.getZanX()).addOnClickListener(R.id.video_icon);
    }
}
