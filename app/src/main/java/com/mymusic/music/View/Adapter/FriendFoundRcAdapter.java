package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.FriendAllData;
import com.mymusic.music.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/6/23 10:44
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendFoundRcAdapter extends BaseQuickAdapter <FriendAllData.DataBean.ListBeanX,BaseViewHolder> {

    public FriendFoundRcAdapter(int layoutResId, @Nullable List<FriendAllData.DataBean.ListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendAllData.DataBean.ListBeanX s) {
        helper.setText(R.id.name,s.getTitle()).setText(R.id.des,s.getDescription());
        Glide.with(mContext).load(s.getIcon()).error(R.drawable.fq_ic_placeholder).into((CircleImageView)helper.getView(R.id.head));
    }
}
