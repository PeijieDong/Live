package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.Art;
import com.mymusic.music.Live;
import com.mymusic.music.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/6/23 21:26
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class ArRcAdpater extends BaseQuickAdapter<Art.DataBean.ListBean,BaseViewHolder> {

    public ArRcAdpater(int layoutResId, @Nullable List<Art.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Art.DataBean.ListBean s) {
        helper.setText(R.id.title,Live.getInstance().get(mContext).getList().getUser_nicename())
                .setText(R.id.time,s.getCreatetime())
                .setText(R.id.detail,s.getContent())
                .setText(R.id.type,s.getType())
                .setText(R.id.des,s.getCatename())
                .addOnClickListener(R.id.close)
                .addOnClickListener(R.id.head);
        Glide.with(mContext).load(Live.getInstance().getUser(mContext).getData().getAvatar_thumb()).error(R.drawable.fq_ic_placeholder).into((CircleImageView) helper.getView(R.id.head));
        Glide.with(mContext).load(s.getImage()).error(R.drawable.fq_ic_placeholder).into((ImageView) helper.getView(R.id.image));
    }
}
