package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.CommentBean;
import com.mymusic.music.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/6/23 0:26
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoFragmentRcAdapter extends BaseQuickAdapter<CommentBean.DataBean.ListBean,BaseViewHolder> {
    public VideoFragmentRcAdapter(int layoutResId, @Nullable List<CommentBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean.DataBean.ListBean item) {
        Glide.with(mContext).load(item.getAvatar()).into((CircleImageView) helper.getView(R.id.detail_head_cir));
        helper.setText(R.id.detail_name,item.getUser_nicename())
                .setText(R.id.detail_time,item.getCreatetime())
                .setText(R.id.detail_content,item.getContent())
                .addOnClickListener(R.id.comment_like);
    }
}
