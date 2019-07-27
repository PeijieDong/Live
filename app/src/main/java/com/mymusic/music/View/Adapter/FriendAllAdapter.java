package com.mymusic.music.View.Adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.FriendAllData;
import com.mymusic.music.R;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/8 9:52
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendAllAdapter extends BaseQuickAdapter<FriendAllData.DataBean.ListBeanX,BaseViewHolder>  {
    LinearLayout ll;
    FriendAllData.DataBean.ListBeanX item;
    private ImageView open;

    public FriendAllAdapter(int layoutResId, @Nullable List<FriendAllData.DataBean.ListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendAllData.DataBean.ListBeanX item) {
        this.item = item;
        helper.setText(R.id.friend_item_title,item.getTitle())
                .setText(R.id.friend_item_des,item.getDescription())
                .setText(R.id.friend_item_focus,"关注"+"\r"+item.getGnum())
                .setText(R.id.friend_item_art,"帖子"+"\r"+item.getTiezi())
                .addOnClickListener(R.id.friend_all_bt)
                .addOnClickListener(R.id.friend_all_down)
                .addOnClickListener(R.id.friend_all_image1)
                .addOnClickListener(R.id.friend_all_image2)
                .addOnClickListener(R.id.friend_all_image3);
        open = helper.getView(R.id.friend_all_down);
        if(item.getList() != null && item.getList().size() != 0){
            Log.d("33",item.getList().size()+"");
            helper.setText(R.id.friend_all_num1,item.getList().get(0).getCate()+"张")
                    .setText(R.id.friend_all_num2,item.getList().get(1).getCate()+"张")
                    .setText(R.id.friend_all_num3,item.getList().get(2).getCate()+"张")
                    .setText(R.id.friend_all_content1,item.getList().get(0).getContent())
                    .setText(R.id.friend_all_content2,item.getList().get(1).getContent())
                    .setText(R.id.friend_all_content3,item.getList().get(2).getContent());
            ImageView image1 = helper.getView(R.id.friend_all_image1);
            ImageView image2 = helper.getView(R.id.friend_all_image2);
            ImageView image3 = helper.getView(R.id.friend_all_image3);
            Glide.with(mContext).load(item.getList().get(0).getImage()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(image1);
            Glide.with(mContext).load(item.getList().get(1).getImage()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(image2);
            Glide.with(mContext).load(item.getList().get(2).getImage()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent).into(image3);
        }else{
            open.setVisibility(View.GONE);
        }
        if(item.getIsguanzhu().equals("已关注")){
            helper.setText(R.id.friend_all_bt,"取消关注");
            helper.setBackgroundRes(R.id.friend_all_bt,R.drawable.isfocus);
        }else{
            helper.setText(R.id.friend_all_bt,"+关注");
            helper.setBackgroundRes(R.id.friend_all_bt,R.drawable.focus);
        }
        if(item.getOfficial().equals("1")){
            helper.setVisible(R.id.guanfang,true);
        }
        ImageView head = helper.getView(R.id.friend_item_head);
        Glide.with(mContext).load(item.getIcon()).error(R.drawable.fq_ic_placeholder).into(head);
        ImageView down = helper.getView(R.id.friend_all_down);
    }


}
