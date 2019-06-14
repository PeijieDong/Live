package com.mymusic.music.View.Adapter;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.FriendFindData;
import com.mymusic.music.R;
import com.mymusic.music.View.Activity.user.ListUserActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/6/1 16:47
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendFindRecyclerviewAdapter extends BaseQuickAdapter<FriendFindData.DataBean.ListBeanX,BaseViewHolder> implements View.OnClickListener {

    public FriendFindRecyclerviewAdapter(int layoutResId, @Nullable List<FriendFindData.DataBean.ListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendFindData.DataBean.ListBeanX item) {
        helper.setText(R.id.friend_find_title,item.getTitle())
                .setText(R.id.friend_find_dec,item.getDescription())
                .setText(R.id.friend_find_type,item.getName())
                .setText(R.id.tv2,item.getIsguanzhu()+"")
                .setText(R.id.tv4,item.getTui())
                .setText(R.id.image_type_1,item.getList().get(0).getType())
                .setText(R.id.image_type_2,item.getList().get(1).getType())
                .setText(R.id.image_type_3,item.getList().get(2).getType())
                .setText(R.id.image_type_4,item.getList().get(3).getType())
                .setText(R.id.image_type_5,item.getList().get(4).getType())
                .setText(R.id.image_type_6,item.getList().get(5).getType());
        ImageView head = helper.getView(R.id.friend_find_head);
        ImageView one = helper.getView(R.id.friend_find_one);
        ImageView two = helper.getView(R.id.friend_find_two);
        ImageView three = helper.getView(R.id.friend_find_three);
        ImageView four = helper.getView(R.id.friend_find_four);
        ImageView five = helper.getView(R.id.friend_find_five);
        ImageView six = helper.getView(R.id.friend_find_six);
        CircleImageView ci1 = (CircleImageView)helper.getView(R.id.ci1);
        CircleImageView ci2 = (CircleImageView)helper.getView(R.id.ci2);
        CircleImageView ci3 = (CircleImageView)helper.getView(R.id.ci3);
        CircleImageView ci4 = (CircleImageView)helper.getView(R.id.ci4);
//        Glide.with(mContext).load(split[0]).into(ci1);
//        Glide.with(mContext).load(split[1]).into(ci2);
//        Glide.with(mContext).load(split[2]).into(ci3);
//        Glide.with(mContext).load(split[3]).into(ci4);
        Glide.with(mContext).load(item.getIcon()).into(head);
        Glide.with(mContext).load(item.getList().get(0).getImage()).into(one);
        Glide.with(mContext).load(item.getList().get(1).getImage()).into(two);
        Glide.with(mContext).load(item.getList().get(2).getImage()).into(three);
        Glide.with(mContext).load(item.getList().get(3).getImage()).into(four);
        Glide.with(mContext).load(item.getList().get(4).getImage()).into(five);
        Glide.with(mContext).load(item.getList().get(5).getImage()).into(six);

        ConstraintLayout fourHead = helper.getView(R.id.four_head);
        fourHead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mContext.startActivity(new Intent(mContext,ListUserActivity.class));
    }
}
