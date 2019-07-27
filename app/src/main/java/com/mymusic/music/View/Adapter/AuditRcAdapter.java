package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.Audit;
import com.mymusic.music.R;

import java.util.List;

/**
 * Create By mr.mao in 2019/7/11 21:46
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class AuditRcAdapter extends BaseQuickAdapter<Audit.DataBean.ListBean,BaseViewHolder> {
    public AuditRcAdapter(int layoutResId, @Nullable List<Audit.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Audit.DataBean.ListBean item) {
        helper.setText(R.id.time,item.getCreatetime())
                .setText(R.id.content,item.getContent())
                .setText(R.id.content_title,item.getTitle());
        if(item.getNtype().equals("视频")){
            helper.setVisible(R.id.video_icon,true);
        }

        if(item.getShenhe().equals("1")){
            helper.setText(R.id.state,"已通过");
            Glide.with(mContext).load(R.drawable.icon_post_review_sucess).into((ImageView) helper.getView(R.id.state_state));
        }else{
            helper.setText(R.id.state,"被驳回");
        }
        Glide.with(mContext).load(item.getImages()).error(R.drawable.icon_post_review_duanwe).into((ImageView) helper.getView(R.id.image));
    }
}
