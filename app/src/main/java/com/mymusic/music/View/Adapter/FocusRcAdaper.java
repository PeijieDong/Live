package com.mymusic.music.View.Adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.Focus;
import com.mymusic.music.R;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/6/7 9:59
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FocusRcAdaper extends BaseQuickAdapter<Focus.DataBean.ListBean,BaseViewHolder>  {

    public FocusRcAdaper(int layoutResId, @Nullable List<Focus.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Focus.DataBean.ListBean item) {
        CircleImageView head = helper.getView(R.id.focus_rc_head);
        helper.setText(R.id.focus_rc_name,item.getUser_nick())
                .setText(R.id.focus_rc_dec,item.getSignature());
        if(item.getGuanzhu().equals("已关注")){
            helper.setText(R.id.focus_rc_focusbt,"取消关注");
            helper.setBackgroundRes(R.id.focus_rc_focusbt,R.drawable.isfocus);
        }else{
            helper.setText(R.id.focus_rc_focusbt,"+关注");
            helper.setBackgroundRes(R.id.focus_rc_focusbt,R.drawable.focus);
        }
        Glide.with(mContext).load(item.getAvatar()).into(head);
        helper.addOnClickListener(R.id.focus_rc_focusbt);
    }


}
