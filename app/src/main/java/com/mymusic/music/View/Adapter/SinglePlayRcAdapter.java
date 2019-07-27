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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.AppUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.MyJzvdStd;
import com.mymusic.music.Util.MyVideoPlayer;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
import com.mymusic.music.View.Activity.JubaoVideoActiviy;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/7/28 3:41
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class SinglePlayRcAdapter extends BaseQuickAdapter<HomeData.DataBean.ListBean,BaseViewHolder> implements View.OnClickListener {
    List<HomeData.DataBean.ListBean> list;
    public SinglePlayRcAdapter(int layoutResId, @Nullable List<HomeData.DataBean.ListBean> data) {
        super(layoutResId, data);
        this.list = data;
    }
    ImageView more;
    TextView focus;
    CircleImageView head;
    @Override
    protected void convert(BaseViewHolder helper, HomeData.DataBean.ListBean s) {
        helper.setText(R.id.video_title,s.getUsername())
                .setText(R.id.video_des,s.getContent())
                .setText(R.id.focus,"+关注");
        head = helper.getView(R.id.head);
        focus = helper.getView(R.id.focus);
        more = helper.getView(R.id.more);
        head.setOnClickListener(this);
        focus.setOnClickListener(this);
        more.setOnClickListener(this);
        Glide.with(mContext).load(s.getAvatar_thumb()).error(R.drawable.fq_ic_placeholder).into((CircleImageView) helper.getView(R.id.head));
        MyVideoPlayer video = helper.getView(R.id.mp_video);
        video.setUp(s.getFilepath(), JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
        video.startVideo();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head:
                if(Live.getInstance().getUser(mContext) == null){
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                    return ;
                }
                Intent intent = new Intent(mContext, UserDetailActivity.class);
                intent.putExtra("UserId",list.get(0).getUid());
                mContext.startActivity(intent);
                break;
            case R.id.focus:
                if(Live.getInstance().getUser(mContext) == null){
                    Intent intent1 = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent1);
                    return;
                }
                focus.setText("取消关注");
                focus.setBackgroundResource(R.drawable.isfocus);
                initNet();
                focus.setClickable(false);
                break;
            case R.id.more:
                initBottom();
                break;
        }
    }

    private void initBottom() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(mContext);//实例化
        bottomSheet.setCancelable(true);//设置点击外部是否可以取消
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_video_layout, null);
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
                CopyText(list.get(0).getContent());
                ToastUtil.show(mContext,"复制成功，快去分享吧！",Toast.LENGTH_SHORT);
                bottomSheet.dismiss();
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Live.getInstance().getUser(mContext) == null){
                    Intent intent1 = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent1);
                    return;
                }
                Intent intent = new Intent(mContext, JubaoVideoActiviy.class);
                intent.putExtra("id",list.get(0).getUid());
                mContext.startActivity(intent);
                bottomSheet.dismiss();
            }
        });
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Live.getInstance().getUser(mContext) == null){
                    Intent intent1 = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent1);
                    return;
                }
                initCollection();
                bottomSheet.dismiss();
            }
        });
        bottomSheet.setContentView(view);//设置对框框中的布局
        bottomSheet.show();//显示弹窗
    }

    private void initCollection() {
        if(Live.getInstance().getToken(mContext) == null){
            Intent intent = new Intent(mContext, LoginActivity.class);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("id",list.get(0).getUid());
        NetRequest.postFormHeadRequest(UrlManager.Vide_Collection, map, Live.getInstance().getToken(mContext), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                ToastUtil.show(mContext,"收藏成功",Toast.LENGTH_SHORT);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(mContext);
                dialog.Show();
            }
        });
    }


    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("type","1");
        map.put("id",list.get(0).getUid());
        NetRequest.postFormRequest(UrlManager.Focus_User, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                ToastUtil.show(mContext,"关注成功",Toast.LENGTH_SHORT);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(mContext);
                dialog.Show();
            }
        });
    }

    private void CopyText(String text) {
        ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(null, text);
        clipboard.setPrimaryClip(clipData);
    }

}
