package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.Fans;
import com.mymusic.music.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/6/22 20:22
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FansRcAdapter extends BaseQuickAdapter<Fans.DataBean.ListBean,BaseViewHolder> {
    TextView focus;
    public FansRcAdapter(int layoutResId, @Nullable List<Fans.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Fans.DataBean.ListBean item) {
        helper.setText(R.id.focus_rc_name,item.getUser_nicename())
                .setText(R.id.focus_rc_dec,item.getSignature());
        Glide.with(mContext).load(item.getAvatar()).error(R.drawable.fq_ic_placeholder).into((CircleImageView) helper.getView(R.id.focus_rc_head));
        focus = helper.getView(R.id.focus_rc_focusbt);
        if(item.getGuanzhu().equals("已关注")){
            focus.setText("取消关注");
            focus.setBackgroundResource(R.drawable.back_friend_detail_cencelfocus);
        }else{
            focus.setText("+关注");
            focus.setBackgroundResource(R.drawable.back_friend_detail_focus);
        }
        helper.addOnClickListener(R.id.focus_rc_focusbt);
    }


}
