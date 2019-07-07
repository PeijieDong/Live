package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.UserVideo;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/30 0:58
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class UserVideoAdapter extends BaseQuickAdapter<UserVideo.DataBean.ListBean,BaseViewHolder> {

    public UserVideoAdapter(int layoutResId, @Nullable List<UserVideo.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserVideo.DataBean.ListBean item) {
        helper.setText(R.id.zan,item.getZan());
        Glide.with(mContext).load(item.getFilepath()).into((ImageView) helper.getView(R.id.video));
    }
}
