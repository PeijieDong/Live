package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.Task;
import com.mymusic.music.R;

import java.util.List;

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
        if(item.getTitle().equals("邀请用户，下载并登陆")){
            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_share));
            helper.getView(R.id.doit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if(item.getTitle().equals("分享帖子或啪啪")){
            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_share));
            helper.getView(R.id.doit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if(item.getTitle().equals("点赞帖子或评论")){
            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_thumb));
            helper.getView(R.id.doit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if(item.getTitle().equals("完成账号注册")){
            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_publish));
            helper.getView(R.id.doit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if(item.getTitle().equals("修改昵称，完善个人资料")){
            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_publish));
            helper.getView(R.id.doit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if(item.getTitle().equals("浏览帖子")){
            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_browse_post));
            helper.getView(R.id.doit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if(item.getTitle().equals("保存推广二维码至相册")){
            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_qcode));
            helper.getView(R.id.doit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if(item.getTitle().equals("收到点赞")){
            helper.setImageDrawable(R.id.task_head,mContext.getResources().getDrawable(R.drawable.task_center_thumb));
            helper.getView(R.id.doit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        helper.setText(R.id.task_title,item.getTitle())
                .setText(R.id.task_num,item.getDesc())
                .setText(R.id.unfinish,item.getNum1()+"/")
                .setText(R.id.total,item.getNum2());
        if(item.getStatus().equals("1")){
            helper.getView(R.id.doit).setClickable(false);
        }
    }
}
