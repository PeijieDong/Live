package com.mymusic.music.View.Adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.R;
import com.mymusic.music.Util.MyGridView;
import com.mymusic.music.View.Activity.Detail.VideoPlayActivity;

import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create By mr.mao in 2019/5/29 22:22
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class HomePagerRecyclerViewAdapter extends BaseQuickAdapter<HomeData.DataBean.ListBean,BaseViewHolder>{

    private final int PICTURE = 1;
    private final int ARTICLE = 2;
    private final int VIDEO = 3;
    private final int RECOMMEND = 4;
    private final int ADV = 5;
    private final int PAPA = 6;
    private final int USER = 7;
    HomeData.DataBean.ListBean item;

    public HomePagerRecyclerViewAdapter(@Nullable List<HomeData.DataBean.ListBean> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<HomeData.DataBean.ListBean>() {
            @Override
            protected int getItemType(HomeData.DataBean.ListBean s) {
                switch (s.getType()){
                    //多图片
                    case "图片":
                    case "4":
                        return PICTURE;
                    //文章
                    case "文字":
                    case "3":
                        return ARTICLE;
                    //视频
                    case "视频":
                    case "5":
                        return VIDEO;
                    //推荐
                    case "推荐":
                        return RECOMMEND;
                    //广告
                    case "广告":
                        return ADV;
                    case "小视频":
                        if(s.getObjs() != null){
                            return PAPA;
                        }else{
                            return VIDEO;
                        }
                    case "用户":
                        return USER;
                }
                return 5;
            }
        });
        getMultiTypeDelegate().registerItemType(PICTURE,R.layout.home_rc_item_picture)
                              .registerItemType(ARTICLE,R.layout.home_rc_item_article)
                              .registerItemType(VIDEO,R.layout.home_rc_video)
                              .registerItemType(RECOMMEND,R.layout.empty_layout)
                              .registerItemType(ADV,R.layout.home_rc_adv)
                              .registerItemType(PAPA,R.layout.found_video_layout)
                              .registerItemType(USER,R.layout.found_user_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeData.DataBean.ListBean item) {
        this.item = item;
        int type = helper.getItemViewType();
        helper.addOnClickListener(R.id.themeBt)
                .addOnClickListener(R.id.ll_home_like)
                .addOnClickListener(R.id.icon_comment)
                .addOnClickListener(R.id.userBt);
        switch (type){
            case PICTURE:
                initListener(helper,item);
                if(item.getZhiding() != null) {
                    if (item.getZhiding().equals("1")) {
                        helper.setVisible(R.id.isTop, true);
                    }
                }
                helper.setText(R.id.likeNum,item.getZan())
                        .setText(R.id.commentNum,item.getComment())
                        .setText(R.id.shareNum,item.getShare())
                        .setText(R.id.username,item.getUsername())
                        .setText(R.id.home_rc_type,item.getCatename())
                        .setText(R.id.tv_content,item.getTitle());
                helper.addOnClickListener(R.id.icon_more);
                Glide.with(mContext).load(item.getAvatar()).error(R.drawable.fq_ic_placeholder).placeholder(R.drawable.fq_bottom_transparent)
                        .into((CircleImageView) helper.getView(R.id.userHead));
                HomeGridAdapter adapter = new HomeGridAdapter(mContext,item.getImages());
                MyGridView grid = helper.getView(R.id.home_rc_grid);
                grid.setAdapter(adapter);
                break;
            case ARTICLE:
                if(item.getZhiding() != null){
                    if(item.getZhiding().equals("1")){
                        helper.setVisible(R.id.isTop,true);
                    }
                }
                initListener(helper, item);
                helper.setText(R.id.likeNum,item.getZan())
                        .setText(R.id.commentNum,item.getComment())
                        .setText(R.id.shareNum,item.getShare())
                        .setText(R.id.username,item.getUsername())
                        .setText(R.id.home_rc_type,item.getCatename())
                        .setText(R.id.tv_content_text,item.getContent())
                        .setText(R.id.tv_content,item.getTitle());
                if(item.getTitle()== null || item.getTitle().equals("")){
                    helper.setText(R.id.tv_content,"暂无标题");
                }
                helper.addOnClickListener(R.id.icon_more);
                TextView text = helper.getView(R.id.tv_content_text);
                TextView vis = helper.getView(R.id.tv_content_vis);
                text.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        text.post(new Runnable() {
                            @Override
                            public void run() {
                                if(text.getLineCount() > 3){
                                    text.setMaxLines(3);
                                    vis.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        text.getViewTreeObserver().removeOnPreDrawListener(this);
                        return false;
                    }
                });
                Glide.with(mContext).load(item.getAvatar()).error(R.drawable.fq_ic_placeholder)
                        .into((CircleImageView) helper.getView(R.id.userHead));
                break;
            case VIDEO:
                if(item.getZhiding() != null) {
                    if (item.getZhiding().equals("1")) {
                        helper.setVisible(R.id.isTop, true);
                    }
                }
                initListener(helper, item);
                helper.setText(R.id.likeNum,item.getZan())
                        .setText(R.id.commentNum,item.getComment())
                        .setText(R.id.shareNum,item.getShare())
                        .setText(R.id.username,item.getUsername())
                        .setText(R.id.home_rc_type,item.getCatename())
                        .setText(R.id.tv_content,item.getTitle())
                        .setText(R.id.play_time,item.getClick()+"次播放")
                        .setText(R.id.play_num,item.getPlaytime());
                helper.addOnClickListener(R.id.icon_more);
                Glide.with(mContext).load(item.getAvatar()).error(R.drawable.fq_ic_placeholder)
                        .into((CircleImageView) helper.getView(R.id.userHead));
                if(item.getImage().equals("")){
                    Glide.with(mContext).load(R.drawable.play_holder).placeholder(R.drawable.fq_bottom_transparent)
                            .error(R.drawable.fq_ic_placeholder).into((ImageView) helper.getView(R.id.video_image));
                }else {
                    Glide.with(mContext).load(item.getImage()).placeholder(R.drawable.fq_bottom_transparent)
                            .error(R.drawable.fq_ic_placeholder).into((ImageView) helper.getView(R.id.video_image));
                }
                break;
            case RECOMMEND:
                break;
            case ADV:
                helper.setText(R.id.title,item.getTitle())
                .setText(R.id.number,item.getNumber()+"人观看中")
                .setText(R.id.name,item.getName());
                Glide.with(mContext).load(item.getImage()).placeholder(R.drawable.fq_bottom_transparent)
                        .error(R.drawable.fq_ic_placeholder).into((ImageView) helper.getView(R.id.adv_image));
                Glide.with(mContext).load(item.getAvatar()).placeholder(R.drawable.fq_bottom_transparent)
                        .error(R.drawable.fq_ic_placeholder).into((ImageView) helper.getView(R.id.head));
                break;
            case PAPA:
                RecyclerView FoundRc = helper.getView(R.id.found_rc);
                FoundRc.setLayoutManager(new GridLayoutManager(mContext,2));
                FoundRcAdapter foundAdapter = new FoundRcAdapter(R.layout.found_video_item,item.getObjs());
                foundAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()){
                            case R.id.video_icon:
                                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                                intent.putExtra("playData",(Serializable) item.getObjs());
                                intent.putExtra("position",position);
                                mContext.startActivity(intent);
                                break;
                        }
                    }
                });
                FoundRc.setAdapter(foundAdapter);
                break;
            case USER:
                helper.setText(R.id.found_name,item.getUser_nicename())
                        .setText(R.id.found_content,item.getSignature())
                        .addOnClickListener(R.id.user_go);
                Glide.with(mContext).load(item.getAvatar_thumb()).error(R.drawable.fq_ic_placeholder).into((ImageView) helper.getView(R.id.found_head));
                break;
        }
    }
    ImageView like;
    TextView likeNum;
    public void initListener(BaseViewHolder helper, HomeData.DataBean.ListBean item){
        like = helper.getView(R.id.icon_like);
        likeNum = helper.getView(R.id.likeNum);
    }




}
