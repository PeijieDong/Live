package com.mymusic.music.View.Adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
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
public class FriendAllAdapter extends BaseQuickAdapter<FriendAllData.DataBean.ListBeanX,BaseViewHolder> implements View.OnClickListener {
    LinearLayout ll;
    FriendAllData.DataBean.ListBeanX item;
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
                .setText(R.id.friend_all_num1,item.getList().get(0).getCate()+"张")
                .setText(R.id.friend_all_num2,item.getList().get(1).getCate()+"张")
                .setText(R.id.friend_all_num3,item.getList().get(2).getCate()+"张")
                .setText(R.id.friend_all_content1,item.getList().get(0).getContent())
                .setText(R.id.friend_all_content2,item.getList().get(1).getContent())
                .setText(R.id.friend_all_content3,item.getList().get(2).getContent());
        ImageView head = helper.getView(R.id.friend_item_head);
        ImageView image1 = helper.getView(R.id.friend_all_image1);
        ImageView image2 = helper.getView(R.id.friend_all_image2);
        ImageView image3 = helper.getView(R.id.friend_all_image3);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        Glide.with(mContext).load(item.getIcon()).into(head);
        Glide.with(mContext).load(item.getList().get(0).getImage()).into(image1);
        Glide.with(mContext).load(item.getList().get(1).getImage()).into(image2);
        Glide.with(mContext).load(item.getList().get(2).getImage()).into(image3);
        ImageView down = helper.getView(R.id.friend_all_down);
        down.setOnClickListener(this);
        ll = helper.getView(R.id.ll_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.friend_all_down:
                int visibility = ll.getVisibility();
                if(visibility == View.GONE){
                    ll.setVisibility(View.VISIBLE);
                }
                if(visibility == View.VISIBLE){
                    ll.setVisibility(View.GONE);
                }
                break;
            case R.id.friend_all_image1:
            case R.id.friend_all_image2:
            case R.id.friend_all_image3:
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("id",item.getList().get(0).getId());
                mContext.startActivity(intent);
                break;
        }
    }

}
