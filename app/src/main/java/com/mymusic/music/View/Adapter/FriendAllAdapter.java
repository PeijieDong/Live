package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.FriendAllData;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/8 9:52
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendAllAdapter extends BaseQuickAdapter<FriendAllData.DataBean.ListBeanX,BaseViewHolder> {

    public FriendAllAdapter(int layoutResId, @Nullable List<FriendAllData.DataBean.ListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendAllData.DataBean.ListBeanX item) {
        helper.setText(R.id.friend_item_title,item.getTitle())
                .setText(R.id.friend_item_des,item.getDescription())
                .setText(R.id.friend_item_focus,item.getGnum())
                .setText(R.id.friend_item_art,item.getTiezi())
                .setText(R.id.friend_all_num1,item.getList().get(0).getCate())
                .setText(R.id.friend_all_num2,item.getList().get(1).getCate())
                .setText(R.id.friend_all_num3,item.getList().get(2).getCate())
                .setText(R.id.friend_all_content1,item.getList().get(0).getContent())
                .setText(R.id.friend_all_content2,item.getList().get(1).getContent())
                .setText(R.id.friend_all_content3,item.getList().get(2).getContent());
        ImageView head = helper.getView(R.id.friend_item_head);
        ImageView image1 = helper.getView(R.id.friend_all_image1);
        ImageView image2 = helper.getView(R.id.friend_all_image2);
        ImageView image3 = helper.getView(R.id.friend_all_image3);
//        Glide.with(mContext).load().into(head);
//        Glide.with(mContext).load().into(image1);
//        Glide.with(mContext).load().into(image2);
//        Glide.with(mContext).load().into(image3);
    }
}
