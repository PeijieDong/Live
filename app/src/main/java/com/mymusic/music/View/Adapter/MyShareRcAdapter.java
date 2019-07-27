package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.MyShare;
import com.mymusic.music.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By MR.D
 * 2019/7/26
 * USE:
 **/
public class MyShareRcAdapter extends BaseQuickAdapter<MyShare.DataBean.ListBean, BaseViewHolder> {

    public MyShareRcAdapter(int layoutResId, @Nullable List<MyShare.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyShare.DataBean.ListBean item) {
        helper.setText(R.id.name,item.getUser_nicename());
        Glide.with(mContext).load(item.getAvatar()).error(R.drawable.fq_ic_placeholder).into((CircleImageView) helper.getView(R.id.head));
    }
}
