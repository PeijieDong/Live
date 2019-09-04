package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.mymusic.music.DataBean.VideoItem;
import com.mymusic.music.R;
import com.mymusic.music.Util.MyGridView;

import java.util.List;

/**
 * Create By mr.mao in 2019/8/4 19:26
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class RcAdpaterVideo extends BaseQuickAdapter <VideoItem.DataBean.ListBeanX,BaseViewHolder> {

    public RcAdpaterVideo( @Nullable List<VideoItem.DataBean.ListBeanX> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<VideoItem.DataBean.ListBeanX>() {
            @Override
            protected int getItemType(VideoItem.DataBean.ListBeanX listBeanX) {
                switch (listBeanX.getType()){
                    case "广告":
                        return 1 ;
                    default:
                        return 2;
                }
            }
        });
        getMultiTypeDelegate().registerItemType(1,R.layout.rc_adapter_item_adv)
                .registerItemType(2,R.layout.rc_adapter_item_video);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoItem.DataBean.ListBeanX s) {
        switch (s.getType()){
            case "广告":
                Glide.with(mContext).load(s.getImage()).error(R.drawable.fq_ic_placeholder).into((ImageView) helper.getView(R.id.adv_image));
                helper.addOnClickListener(R.id.adv_image);
                break;
            default:
                helper.addOnClickListener(R.id.more);
                MyGridView grid2 = helper.getView(R.id.RecyclerGrid);
                grid2.setAdapter(new Grid2Adapter(mContext,s.getList()));
                helper.setText(R.id.HomeListTitle,s.getTitle());
                break;
        }

    }
}
