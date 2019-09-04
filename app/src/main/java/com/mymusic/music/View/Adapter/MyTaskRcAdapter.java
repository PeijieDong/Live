package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.Task;
import com.mymusic.music.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/7/11 21:28
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class MyTaskRcAdapter extends BaseQuickAdapter<Task.DataBean.ListBeanX.ListBean,BaseViewHolder> {

    public MyTaskRcAdapter(int layoutResId, @Nullable List<Task.DataBean.ListBeanX.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Task.DataBean.ListBeanX.ListBean item) {
        helper.addOnClickListener(R.id.doit);
//        if(item.getTitle().equals("邀请用户，下载并登陆")){
//            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_share));
//        }
//        if(item.getTitle().equals("分享帖子或啪啪")){
//            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_share));
//        }
//        if(item.getTitle().equals("点赞帖子或评论")){
//            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_thumb));
//        }
//        if(item.getTitle().equals("完成账号注册")){
//            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_publish));
//        }
//        if(item.getTitle().equals("修改昵称，完善个人资料")){
//            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_publish));
//        }
//        if(item.getTitle().equals("浏览帖子")){
//            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_browse_post));
//        }
//        if(item.getTitle().equals("保存推广二维码至相册")){
//            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_qcode));
//        }
//        if(item.getTitle().equals("收到点赞")){
//            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_thumb));
//        }
        helper.setText(R.id.task_title,item.getTitle())
                .setText(R.id.task_num,item.getDesc())
                .setText(R.id.unfinish,item.getTotalValue()+"/")
                .setText(R.id.total,item.getExperience());
        Glide.with(mContext).load(item.getIcon()).error(R.drawable.fq_ic_placeholder).into((CircleImageView) helper.getView(R.id.task_head));
        if(item.getTotalValue().equals(item.getExperience())){
            helper.setBackgroundRes(R.id.doit,R.drawable.isfocus);
            helper.setText(R.id.doit,"已完成");
            helper.getView(R.id.doit).setClickable(false);
        }
//        if(item.getStatus().equals("1")){
//            helper.setBackgroundRes(R.id.doit,R.drawable.isfocus);
//            helper.setText(R.id.doit,"已完成");
//            helper.getView(R.id.doit).setClickable(false);
//        }
    }
}
