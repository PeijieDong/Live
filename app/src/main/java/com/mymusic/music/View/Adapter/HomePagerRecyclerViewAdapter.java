package com.mymusic.music.View.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.R;
import com.mymusic.music.Util.MyGridView;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.FriendDetailActivity;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/5/29 22:22
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class HomePagerRecyclerViewAdapter extends BaseQuickAdapter<HomeData.DataBean.ListBean,BaseViewHolder> implements View.OnClickListener {

    private final int PICTURE = 1;
    private final int ARTICLE = 2;
    private final int VIDEO = 3;
    private final int RECOMMEND = 4;
    private final int ADV = 5;
    HomeData.DataBean.ListBean item;

    public HomePagerRecyclerViewAdapter(@Nullable List<HomeData.DataBean.ListBean> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<HomeData.DataBean.ListBean>() {
            @Override
            protected int getItemType(HomeData.DataBean.ListBean s) {
                switch (s.getType()){
                    //多图片
                    case "图片":
                        return PICTURE;
                    //文章
                    case "文字":
                        return ARTICLE;
                    //视频
                    case "视频":
                        return VIDEO;
                    //推荐
                    case "推荐":
                        return RECOMMEND;
                    //广告
                    case "关注":
                        return ADV;
                }
                return 5;
            }
        });
        getMultiTypeDelegate().registerItemType(PICTURE,R.layout.home_rc_item_picture)
                              .registerItemType(ARTICLE,R.layout.home_rc_item_article)
                              .registerItemType(VIDEO,R.layout.home_rc_video)
                              .registerItemType(RECOMMEND,R.layout.home_rc_recommend)
                              .registerItemType(ADV,R.layout.home_rc_adv);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeData.DataBean.ListBean item) {
        this.item = item;
        int type = helper.getItemViewType();
        helper.addOnClickListener(R.id.themeBt);
        helper.addOnClickListener(R.id.icon_like);
        switch (type){
            case PICTURE:
                initListener(helper,item);
                helper.setText(R.id.likeNum,item.getZan())
                        .setText(R.id.commentNum,item.getComment())
                        .setText(R.id.shareNum,item.getShare())
                        .setText(R.id.username,item.getUsername())
                        .setText(R.id.home_rc_type,item.getCatename())
                        .setText(R.id.tv_content,item.getContent());
                Glide.with(mContext).load(item.getAvatar())
                        .into((CircleImageView) helper.getView(R.id.userHead));
                HomeGridAdapter adapter = new HomeGridAdapter(mContext,item.getImages());
                MyGridView grid = helper.getView(R.id.home_rc_grid);
                grid.setAdapter(adapter);
                break;
            case ARTICLE:
                initListener(helper, item);
                helper.setText(R.id.likeNum,item.getZan())
                        .setText(R.id.commentNum,item.getComment())
                        .setText(R.id.shareNum,item.getShare())
                        .setText(R.id.username,item.getUsername())
                        .setText(R.id.home_rc_type,item.getCatename())
                        .setText(R.id.tv_content,item.getContent());
                TextView text = helper.getView(R.id.tv_content_text);
                TextView vis = helper.getView(R.id.tv_content_vis);
                int count = text.getLineCount();
                if(count == 3){
                    text.setMaxLines(3);
                    vis.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(item.getAvatar())
                        .into((CircleImageView) helper.getView(R.id.userHead));
                break;
            case VIDEO:
                initListener(helper, item);
                helper.setText(R.id.likeNum,item.getZan())
                        .setText(R.id.commentNum,item.getComment())
                        .setText(R.id.shareNum,item.getShare())
                        .setText(R.id.username,item.getUsername())
                        .setText(R.id.home_rc_type,item.getCatename())
                        .setText(R.id.tv_content,item.getContent());
                Glide.with(mContext).load(item.getAvatar())
                        .into((CircleImageView) helper.getView(R.id.userHead));
                Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.video_image));
                break;
            case RECOMMEND:
                break;
            case ADV:
                break;
        }
    }
    ImageView like;
    TextView likeNum;
    public void initListener(BaseViewHolder helper, HomeData.DataBean.ListBean item){
        like = helper.getView(R.id.icon_like);

        likeNum = helper.getView(R.id.likeNum);
        ImageView comment = helper.getView(R.id.icon_comment);
        comment.setOnClickListener(this);
        ImageView share = helper.getView(R.id.icon_share);
        share.setOnClickListener(this);
        ImageView more = helper.getView(R.id.icon_more);
        more.setOnClickListener(this);
        LinearLayout userBt = helper.getView(R.id.userBt);
        userBt.setOnClickListener(this);
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_comment:
                initComment();
                break;
            case R.id.icon_share:
                initShare();
                break;
            case R.id.icon_more:
                showBottomSheetDialog();
                break;
            case R.id.userBt:
                Intent intent1 = new Intent(mContext, UserDetailActivity.class);
                mContext.startActivity(intent1);
                break;
        }
    }

    private void initComment() {

    }
    private void initShare() {
        ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(null, item.getContent());
        clipboard.setPrimaryClip(clipData);
        Toast.makeText(mContext,"分享内容复制成功",Toast.LENGTH_SHORT).show();
    }
    void showBottomSheetDialog(){
        BottomSheetDialog bottomSheet = new BottomSheetDialog(mContext);//实例化
        bottomSheet.setCancelable(true);//设置点击外部是否可以取消
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_layout, null);
        TextView cencel = view.findViewById(R.id.cencel);
        cencel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
            }
        });
        bottomSheet.setContentView(view);//设置对框框中的布局
        bottomSheet.show();//显示弹窗
    }



}
