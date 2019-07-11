package com.mymusic.music.View.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.CommentBean;
import com.mymusic.music.DataBean.Play;
import com.mymusic.music.DataBean.VideoData;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
import com.mymusic.music.View.Activity.JubaoVideoActiviy;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MytaskActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MywalletActivity;
import com.mymusic.music.View.Activity.WebActivity;
import com.mymusic.music.View.Activity.post.PutVideoActivity;
import com.mymusic.music.base.BaseRecAdapter;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/7/1 23:19
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoRcAdapter  extends BaseRecAdapter<VideoData.DataBean.ListBean, VideoViewHolder> {
    public Context context;
    List<VideoData.DataBean.ListBean> list ;
    public static final int CURRENT_STATE_PAUSE = 5;
    private long pressedTime = 0;
    private int position;
    private RecyclerView Rc;
    private VideoViewHolder holder;
    public VideoRcAdapter(Context context, List<VideoData.DataBean.ListBean> list) {
        super(list);
        this.context = context;
        this.list = list;
    }

    @Override
    public void onHolder(VideoViewHolder holder, VideoData.DataBean.ListBean bean, int position) {
        //设置视频大小
        this.holder = holder;
        this.position = position;
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        holder.mp_video.setUp(bean.getFilepath(), JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
        if (position == 0) {
            holder.mp_video.startVideo();
            initPlay(list.get(0).getVid());
        }
//        Glide.with(context).load(bean).into(holder.mp_video.thumbImageView);
        holder.title.setText(bean.getSharecontent());
        holder.des.setText(bean.getUser_nicename());
    }

    private void initPlay(String position) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",position);
        NetRequest.postFormHeadRequest(UrlManager.Play_Num, map, Live.getInstance().getToken(context), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Play bean = GsonUtil.GsonToBean(result, Play.class);
                if(bean.getData().getList().getCount() == 997){
                    holder.mp_video.onStatePause();
                    holder.noNum.setVisibility(View.VISIBLE);
                    holder.noMoneyTitle.setText("免费观看已用完，消耗积分/番茄币享今日无限观看\n当前积分"+bean.getData().getList().getScore()
                            +"个，番茄币"+bean.getData().getList().getMoney()+"个");
                    if(Integer.parseInt(bean.getData().getList().getMoney()) < 10){
                        holder.goMoney.setText("充值番茄币");
                        holder.goMoney.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, MywalletActivity.class);
                                context.startActivity(intent);
                            }
                        });
                    }else{
                        holder.goMoney.setText("10币观看");
                        holder.goMoney.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                initLook("1");
                            }
                        });
                    }
                    if(Integer.parseInt(bean.getData().getList().getScore()) < 10){
                        holder.goLook.setText("赚取积分");
                        holder.goLook.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, MytaskActivity.class);
                                context.startActivity(intent);
                            }
                        });
                    }else{
                        holder.goLook.setText("10积分观看");
                        holder.goLook.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                initLook("0");
                            }
                        });
                    }
                }
            }
            @Override
            public void requestFailure(Request request, IOException e) {
            }
            @Override
            public void TokenFail() {
            }
        });
    }

    private void initLook(String type) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type",type);
        NetRequest.postFormHeadRequest(UrlManager.GoMoney, map, Live.getInstance().getToken(context), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                holder.mp_video.startVideo();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }

    private void initCollection() {
        if(Live.getInstance().getToken(context) == null){
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("id",list.get(position).getVid());
        NetRequest.postFormHeadRequest(UrlManager.Vide_Collection, map, Live.getInstance().getToken(context), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(context);
                dialog.Show();
            }
        });
    }


    @Override
    public VideoViewHolder onCreateHolder() {
        return new VideoTwoViewHolder(getViewByRes(R.layout.video_two_layout));
    }



    private void CopyText(String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(null, text);
        clipboard.setPrimaryClip(clipData);
        holder.shareNum.setText(Integer.parseInt(holder.shareNum.getText().toString())+1+"");
    }
}

