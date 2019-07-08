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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.CommentBean;
import com.mymusic.music.DataBean.VideoData;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
import com.mymusic.music.View.Activity.JubaoVideoActiviy;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.post.PutVideoActivity;
import com.mymusic.music.base.BaseRecAdapter;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;

/**
 * Create By MR.D
 * 2019/5/30
 * USE:视频页Rc适配器
 **/

public class VideoRecyclerViewAdapter extends BaseRecAdapter<VideoData.DataBean.ListBean, VideoViewHolder> {
    public Context context;
    List<VideoData.DataBean.ListBean> list ;
    public static final int CURRENT_STATE_PAUSE = 5;
    private long pressedTime = 0;
    private int position;
    private RecyclerView Rc;
    private VideoViewHolder holder;
    private VideoFragmentRcAdapter adapter;
    private VideoListener listener;
    public VideoRecyclerViewAdapter(Context context, List<VideoData.DataBean.ListBean> list) {
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
        holder.putVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PutVideoActivity.class);
                context.startActivity(intent);
            }
        });
        holder.focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put("touid",list.get(position).getUid());
                NetRequest.postFormHeadRequest(UrlManager.Focus_User, map, Live.getInstance().getToken(context), new NetRequest.DataCallBack() {
                    @Override
                    public void requestSuccess(String result) throws Exception {
                        Toast.makeText(context,"关注成功",Toast.LENGTH_SHORT).show();
                        holder.focus.setVisibility(View.GONE);
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
        });
        holder.video_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheet = new BottomSheetDialog(context);//实例化
                bottomSheet.setCancelable(true);//设置点击外部是否可以取消
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_video_layout, null);
                TextView cencel = view.findViewById(R.id.cencel);
                LinearLayout copy = view.findViewById(R.id.copy);
                LinearLayout feedback = view.findViewById(R.id.feedback);
                LinearLayout collection = view.findViewById(R.id.collection);
                cencel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheet.dismiss();
                    }
                });
                copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CopyText(list.get(position).getContent());
                        Toast.makeText(context,"复制成功，快去分享吧！",Toast.LENGTH_SHORT).show();
                        bottomSheet.dismiss();
                    }
                });
                feedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, JubaoVideoActiviy.class);
                        intent.putExtra("id",list.get(position).getId());
                        context.startActivity(intent);
                        bottomSheet.dismiss();
                    }
                });
                collection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initCollection();
                        bottomSheet.dismiss();
                    }
                });
                bottomSheet.setContentView(view);//设置对框框中的布局
                bottomSheet.show();//显示弹窗
            }
        });
        holder.video_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLike(holder);
            }
        });
        holder.video_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initNet();
            }
        });
        holder.video_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Live.getInstance().getUser(context) == null){
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    return ;
                }
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("UserId",list.get(position).getUid());
                context.startActivity(intent);
            }
        });
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.click(v,position);
            }
        });
        if (position == 0) {
            holder.mp_video.startVideo();
        }
//        Glide.with(context).load(bean).into(holder.mp_video.thumbImageView);
        Glide.with(context).load(bean.getAvatar_thumb()).into(holder.video_head);
        holder.title.setText(bean.getSharecontent());
        holder.des.setText(bean.getUser_nicename());
        holder.likeNum.setText(bean.getZan());
        holder.commentNum.setText(bean.getComment());
        holder.shareNum.setText(bean.getShare());
    }

    private void initCollection() {
        if(Live.getInstance().getToken(context) == null){
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("id",list.get(position).getId());
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


    private void initLike(VideoViewHolder holder) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type","1");
        map.put("id",list.get(position).getId());
        NetRequest.postFormRequest(UrlManager.Video_Comment, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(context,"点赞成功",Toast.LENGTH_SHORT).show();
                holder.likeNum.setText(Integer.parseInt(holder.likeNum.getText().toString())+1+"");
                holder.video_like.setImageResource(R.drawable.yp_video_like);
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
        return new VideoViewHolder(getViewByRes(R.layout.video_item_layout));
    }
    public void goActivity(Class<?> clazz){
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    void showBottomSheetDialog(List<CommentBean.DataBean.ListBean> listbean){
        BottomSheetDialog bottomSheet = new BottomSheetDialog(context);//实例化
        bottomSheet.setCancelable(true);//设置点击外部是否可以取消
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_comment_layout, null);
        TextView post = view.findViewById(R.id.detail_post);
        EditText commentEt = view.findViewById(R.id.comment_et);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(commentEt.getText().toString().equals("")){
                    Toast.makeText(context,"不能为空哦",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Live.getInstance().getToken(context) == null){
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                HashMap<String, String> map = new HashMap<>();
                Log.e("33",list.get(position).getVid());
                map.put("vid",list.get(position).getVid());
                map.put("pid","0");
                map.put("content",commentEt.getText().toString());
                NetRequest.postFormHeadRequest(UrlManager.Comment_Video, map, Live.getInstance().getToken(context), new NetRequest.DataCallBack() {
                    @Override
                    public void requestSuccess(String result) throws Exception {
                        Log.e("33",result);
                        Toast.makeText(context,"提交成功",Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        commentEt.setText("");
                        holder.commentNum.setText(Integer.parseInt(holder.commentNum.getText().toString())+1+"");
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
        });
        Rc = view.findViewById(R.id.Rc);
        Rc.setLayoutManager(new LinearLayoutManager(context));
        adapter = new VideoFragmentRcAdapter(R.layout.video_comment_layout, listbean);
        adapter.setEmptyView(LayoutInflater.from(context).inflate(R.layout.empty_layout, null));
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView likeNum = (TextView) adapter.getViewByPosition(Rc, position, R.id.comment_likeNum);
                ImageView likeIcon = (ImageView) adapter.getViewByPosition(Rc, position, R.id.comment_like);
                switch (view.getId()){
                    case R.id.detail_head_cir:
                        Intent intent = new Intent(context, UserDetailActivity.class);
                        intent.putExtra("UserId",listbean.get(position).getUid());
                        context.startActivity(intent);
                        break;
                    case R.id.comment_like:
                        HashMap<String, String> map = new HashMap<>();
                        map.put("type","1");
                        map.put("id",list.get(position).getId());
                        NetRequest.postFormRequest(UrlManager.Video_Comment, map, new NetRequest.DataCallBack() {
                            @Override
                            public void requestSuccess(String result) throws Exception {
                                Toast.makeText(context,"点赞成功",Toast.LENGTH_SHORT).show();
                                likeNum.setText(Integer.parseInt(holder.likeNum.getText().toString())+1+"");
                                likeIcon.setImageResource(R.drawable.video_favor_s);
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
                        break;
                }
            }
        });
        Rc.setAdapter(adapter);
        bottomSheet.setContentView(view);//设置对框框中的布局
        bottomSheet.show();//显示弹窗

    }
    //评论
    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",list.get(position).getVid());
        map.put("sortby","new");
        map.put("uid","0");
        NetRequest.postFormRequest(UrlManager.Detail_Comment, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                CommentBean bean = GsonUtil.GsonToBean(result, CommentBean.class);
                List<CommentBean.DataBean.ListBean> list = bean.getData().getList();
                showBottomSheetDialog(list);
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

    private void CopyText(String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(null, text);
        clipboard.setPrimaryClip(clipData);
        holder.shareNum.setText(Integer.parseInt(holder.shareNum.getText().toString())+1+"");
    }

    public interface VideoListener{
        void click(View v, int position);
    }

    public void setListener(VideoListener listener){
        this.listener = listener;
    }
}

