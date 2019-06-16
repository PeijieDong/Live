package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.FriendDetail;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/9 21:58
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendDetailAdapter extends BaseQuickAdapter<FriendDetail.DataBean.ListBean,BaseViewHolder> {

    public FriendDetailAdapter(int layoutResId, @Nullable List<FriendDetail.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendDetail.DataBean.ListBean item) {
        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.video_image));
//        helper.setText(R.id.video_time,item.)
    }
}
