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
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.user.ListUserActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/6/1 16:47
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendFindRecyclerviewAdapter extends BaseQuickAdapter<FriendFindData.DataBean.ListBeanX,BaseViewHolder> {
    FriendFindData.DataBean.ListBeanX item;
    public FriendFindRecyclerviewAdapter(int layoutResId, @Nullable List<FriendFindData.DataBean.ListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendFindData.DataBean.ListBeanX item) {
        this.item = item;
        helper.setText(R.id.friend_find_title,item.getTitle())
                .setText(R.id.friend_find_dec,item.getDescription())
                .setText(R.id.friend_find_type,item.getName())
                .setText(R.id.tv2,item.getGnum()+"")
                .setText(R.id.tv4,item.getTui())
                .setText(R.id.image_type_1,item.getList().get(0).getType())
                .setText(R.id.image_type_2,item.getList().get(1).getType())
                .setText(R.id.image_type_3,item.getList().get(2).getType())
                .setText(R.id.image_type_4,item.getList().get(3).getType())
                .setText(R.id.image_type_5,item.getList().get(4).getType())
                .setText(R.id.image_type_6,item.getList().get(5).getType())
                .addOnClickListener(R.id.find_item_focus)
                .addOnClickListener(R.id.four_head)
                .addOnClickListener(R.id.friend_list)
                .addOnClickListener(R.id.image_Container1)
                .addOnClickListener(R.id.image_Container2)
                .addOnClickListener(R.id.image_Container3)
                .addOnClickListener(R.id.image_Container4)
                .addOnClickListener(R.id.image_Container5)
                .addOnClickListener(R.id.image_Container6);
        if(item.getIsguanzhu().equals("已关注")){
            helper.setText(R.id.find_item_focus,"取消关注");
            helper.setBackgroundRes(R.id.find_item_focus,R.drawable.isfocus);
        }else{
            helper.setText(R.id.find_item_focus,"+关注");
            helper.setBackgroundRes(R.id.find_item_focus,R.drawable.focus);
        }
        ImageView head = helper.getView(R.id.friend_find_head);
        ImageView one = helper.getView(R.id.friend_find_one);
        ImageView two = helper.getView(R.id.friend_find_two);
        ImageView three = helper.getView(R.id.friend_find_three);
        ImageView four = helper.getView(R.id.friend_find_four);
        ImageView five = helper.getView(R.id.friend_find_five);
        ImageView six = helper.getView(R.id.friend_find_six);
        ImageView next = helper.getView(R.id.next);
        if(item.getUlist().getHead1().equals("")){
            next.setVisibility(View.GONE);
        }
        CircleImageView ci1 = (CircleImageView)helper.getView(R.id.ci1);
        CircleImageView ci2 = (CircleImageView)helper.getView(R.id.ci2);
        CircleImageView ci3 = (CircleImageView)helper.getView(R.id.ci3);
        CircleImageView ci4 = (CircleImageView)helper.getView(R.id.ci4);
        Glide.with(mContext).load(item.getUlist().getHead1()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(ci1);
        Glide.with(mContext).load(item.getUlist().getHead2()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(ci2);
        Glide.with(mContext).load(item.getUlist().getHead3()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(ci3);
        Glide.with(mContext).load(item.getUlist().getHead4()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(ci4);
        Glide.with(mContext).load(item.getIcon()).into(head);
        Glide.with(mContext).load(item.getList().get(0).getImage()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(one);
        Glide.with(mContext).load(item.getList().get(1).getImage()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(two);
        Glide.with(mContext).load(item.getList().get(2).getImage()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(three);
        Glide.with(mContext).load(item.getList().get(3).getImage()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(four);
        Glide.with(mContext).load(item.getList().get(4).getImage()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(five);
        Glide.with(mContext).load(item.getList().get(5).getImage()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(six);
    }


}
