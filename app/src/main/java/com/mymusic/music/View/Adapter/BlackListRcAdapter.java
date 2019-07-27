package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.BlackList;
import com.mymusic.music.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/7/28 2:14
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class BlackListRcAdapter extends BaseQuickAdapter<BlackList.ListBean,BaseViewHolder> {

    public BlackListRcAdapter(int layoutResId, @Nullable List<BlackList.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BlackList.ListBean s) {
        helper.setText(R.id.title,s.getUser_nicename())
                .setText(R.id.des,s.getSignature())
                .addOnClickListener(R.id.jiechu);
        Glide.with(mContext).load(s.getAvatar_thumb()).error(R.drawable.fq_ic_placeholder).into((CircleImageView) helper.getView(R.id.head));
    }
}
