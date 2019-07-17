package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.UserList;
import com.mymusic.music.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/6/16 21:36
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class ListUserRcAdapter extends BaseQuickAdapter<UserList.DataBean.ListBean,BaseViewHolder> implements View.OnClickListener {
    TextView focus;
    public ListUserRcAdapter(int layoutResId, @Nullable List<UserList.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserList.DataBean.ListBean item) {
        helper.setText(R.id.focus_rc_name,item.getUser_nick())
                .setText(R.id.focus_rc_dec,item.getSignature())
                .addOnClickListener(R.id.focus);
        Glide.with(mContext).load(item.getAvatar()).error(R.drawable.fq_ic_placeholder).into((CircleImageView) helper.getView(R.id.focus_rc_head));
        focus = helper.getView(R.id.focus_rc_focusbt);
        if(item.getGuanzhu().equals("关注")){
            focus.setText("+关注");
            focus.setBackgroundResource(R.drawable.focus);
        }else{
            focus.setText("取消关注");
            focus.setBackgroundResource(R.drawable.isfocus);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
