package com.mymusic.music.View.Adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.CommentData;
import com.mymusic.music.R;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/6/17 0:56
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class DetailCommentRcAdapter extends BaseQuickAdapter <CommentData.DataBean.ListBean,BaseViewHolder> implements View.OnClickListener {
    CommentData.DataBean.ListBean item;
    public DetailCommentRcAdapter(int layoutResId, @Nullable List<CommentData.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentData.DataBean.ListBean item) {
        this.item = item;
        helper.setText(R.id.detail_name,item.getUser_nicename())
                .setText(R.id.detail_time,item.getCreatetime())
                .setText(R.id.detail_content,item.getContent())
                .setText(R.id.detail_like_num,item.getNum());
        CircleImageView head = helper.getView(R.id.detail_head_cir);
        Glide.with(mContext).load(item.getAvatar()).into(head);
        head.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_head_cir:
                Intent intent = new Intent(mContext, UserDetailActivity.class);
                intent.putExtra("UserId",item.getUid());
                mContext.startActivity(intent);
                break;
        }
    }
}
