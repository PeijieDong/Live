package com.mymusic.music.View.Activity.Detail;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mymusic.music.DataBean.CommentData;
import com.mymusic.music.DataBean.DetailData;
import com.mymusic.music.DataBean.Details;
import com.mymusic.music.DataBean.Play;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.AppUtil;
import com.mymusic.music.Util.DiyView.SwitchButton;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.MyGridView;
import com.mymusic.music.Util.MyJzvdStd;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.JubaoActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MytaskActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MywalletActivity;
import com.mymusic.music.View.Adapter.DetailCommentRcAdapter;
import com.mymusic.music.View.Adapter.HomeGridAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.detail_name)
    TextView detail_name;
    @BindView(R.id.detail_time)
    TextView detail_time;
    @BindView(R.id.home_rc_type)
    TextView home_rc_type;
    @BindView(R.id.shareNum)
    TextView shareNum;
    @BindView(R.id.commentNum)
    TextView commentNum;
    @BindView(R.id.detail_head)
    CircleImageView detail_head;
    @BindView(R.id.video_play)
    MyJzvdStd VideoPlay;
    @BindView(R.id.detail_ll)
    LinearLayout detail_ll;
    @BindView(R.id.detail_Rc)
    RecyclerView detailRc;
    @BindView(R.id.switchBt)
    SwitchButton switchBt;
    @BindView(R.id.detail_et)
    EditText detailEt;
    @BindView(R.id.icon_like)
    ImageView likeicon;
    @BindView(R.id.likeNum)
    TextView likenum;
    @BindView(R.id.detail_focus)
    TextView focus;
    @BindView(R.id.hot)
    TextView hot;
    @BindView(R.id.rl_play)
    RelativeLayout rlPlay;
    @BindView(R.id.detail_sex)
    ImageView detail_sex;
    @BindView(R.id.detail_title)
    TextView title;
    @BindView(R.id.icon_comment)
    ImageView collectionBt;
    @BindView(R.id.hotIcon)
    ImageView hotIcon;
    @BindView(R.id.play_layout)
    LinearLayout playLayout;
    @BindView(R.id.other_playout)
    LinearLayout otherPlayout;


    @BindView(R.id.close_video)
    ImageView closeVideo;
    @BindView(R.id.detail_head2)
    CircleImageView head2;
    @BindView(R.id.detail_name2)
    TextView name2;
    @BindView(R.id.detail_sex2)
    ImageView sex2;
    @BindView(R.id.detail_time2)
    TextView time2;
    @BindView(R.id.detail_focus2)
    TextView focus3;
    @BindView(R.id.play_content)
    TextView content;
    @BindView(R.id.friend_name)
    TextView friendName;
    @BindView(R.id.playNum)
    TextView playNum;
    @BindView(R.id.no_num)
    ConstraintLayout noNum;
    @BindView(R.id.noMoneyTitle)
    TextView noMoneyTitle;
    @BindView(R.id.goLook)
    TextView goLook;
    @BindView(R.id.goMoney)
    TextView goMoney;


    private String id;
    private DetailCommentRcAdapter adapter;
    private DetailData data;
    private boolean isFocus = false;
    private List<CommentData.DataBean.ListBean> list = new ArrayList<>();

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_details);
    }
    @Override
    protected void initVariables(Intent intent) {
        id = intent.getStringExtra("id");
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        NetRequest.getFormRequest(UrlManager.HOME_DETAILS, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                data = GsonUtil.GsonToBean(result, DetailData.class);
                initView(data);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }


    private void initView(DetailData data) {
        if(data.getData().getList().getType().equals("视频")){
            VideoPlay.setVisibility(View.VISIBLE);
            rlPlay.setVisibility(View.VISIBLE);
            playLayout.setVisibility(View.VISIBLE);
            otherPlayout.setVisibility(View.GONE);
            title.setText("视频详情");
            VideoPlay.setUp(data.getData().getList().getVideourl(),
                    JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
            VideoPlay.startVideo();
            initPlay(data.getData().getList().getId());
            Glide.with(this).load(data.getData().getList().getImage()).into(VideoPlay.thumbImageView);
        }else if(data.getData().getList().getType().equals("文字")){
            title.setText("短文详情");
            TextView view = (TextView) LayoutInflater.from(this).inflate(R.layout.detail_text_item, null);
            view.setText(data.getData().getList().getContent());
            detail_ll.addView(view);
        }else if(data.getData().getList().getType().equals("图片")){
            title.setText("图片详情");
            View view = LayoutInflater.from(this).inflate(R.layout.home_rc_pic, null);
            TextView content = view.findViewById(R.id.tv_content);
            MyGridView grid = view.findViewById(R.id.home_rc_grid);
            content.setText(data.getData().getList().getContent());
            HomeGridAdapter adapter = new HomeGridAdapter(this,data.getData().getList().getImages());
            grid.setAdapter(adapter);
            detail_ll.addView(view);
        }
        switch (data.getData().getList().getSex()){
            case "1":
                //男
                sex2.setImageResource(R.drawable.fq_ic_gender_male);
                detail_sex.setImageResource(R.drawable.fq_ic_gender_male);
                break;
            case "2":
                sex2.setImageResource(R.drawable.fq_ic_gender_female);
                detail_sex.setImageResource(R.drawable.fq_ic_gender_female);
                break;
        }
        name2.setText(data.getData().getList().getUsername());
        detail_name.setText(data.getData().getList().getUsername());
        time2.setText(data.getData().getList().getCreatetime());
        content.setText(data.getData().getList().getContent());
        friendName.setText("#"+data.getData().getList().getCatename()+"#");
        playNum.setText("|  "+data.getData().getList().getClick()+"次浏览");
        detail_time.setText(data.getData().getList().getCreatetime()+"  "+data.getData().getList().getClick()+"次浏览");
        home_rc_type.setText(data.getData().getList().getCatename());
        shareNum.setText(data.getData().getList().getShare());
        commentNum.setText(data.getData().getList().getCollect());
        likenum.setText(data.getData().getList().getZan());
        Glide.with(this).load(data.getData().getList().getAvatar()).into(detail_head);
        Glide.with(this).load(data.getData().getList().getAvatar()).into(head2);
        initComment("new","0");
    }

    private void initPlay(String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("client", AppUtil.getSerialNumber());
        NetRequest.postFormHeadRequest(UrlManager.Play_Num, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Play bean = GsonUtil.GsonToBean(result, Play.class);
                if(bean.getData().getList().getCount() == 997){
                    VideoPlay.onStatePause();
                    noNum.setVisibility(View.VISIBLE);
                    noMoneyTitle.setText("免费观看已用完，消耗积分/番茄币享今日无限观看\n当前积分"+bean.getData().getList().getScore()
                            +"个，番茄币"+bean.getData().getList().getMoney()+"个");
                    if(Integer.parseInt(bean.getData().getList().getMoney()) < 10){
                        goMoney.setText("充值番茄币");
                        goMoney.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(DetailsActivity.this, MywalletActivity.class);
                                startActivity(intent);
                            }
                        });
                    }else{
                        goMoney.setText("10币观看");
                        goMoney.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                initLook("1");
                            }
                        });
                    }
                    if(Integer.parseInt(bean.getData().getList().getScore()) < 10){
                        goLook.setText("赚取积分");
                        goLook.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(DetailsActivity.this, MytaskActivity.class);
                                startActivity(intent);
                            }
                        });
                    }else{
                        goLook.setText("10积分观看");
                        goLook.setOnClickListener(new View.OnClickListener() {
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
        NetRequest.postFormHeadRequest(UrlManager.GoMoney, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                VideoPlay.startVideo();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }

    private void initComment(String type,String somebody) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("sortby",type);
        map.put("uid",somebody);
        NetRequest.getFormRequest(UrlManager.Detail_Comment, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                CommentData bean = GsonUtil.GsonToBean(result, CommentData.class);

                list = bean.getData().getList();
                initCommentList(list);

            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }

    private void initCommentList(List<CommentData.DataBean.ListBean> list) {
        detailRc.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DetailCommentRcAdapter(R.layout.detail_item_layout,list);
        View view2 = LayoutInflater.from(this).inflate(R.layout.empty_layout, null);
        adapter.setEmptyView(view2);
        View view = LayoutInflater.from(this).inflate(R.layout.null_comment, null);
        if(list == null || list.size() == 0){
            adapter.addFooterView(view);
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//                Intent intent = new Intent(DetailsActivity.this, CommentDetailActivity.class);
//                intent.putExtra("commentList",list.get(i));
//                startActivity(intent);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TextView likeNum = (TextView) adapter.getViewByPosition(detailRc,i, R.id.detail_like_num);
                ImageView noLike = (ImageView) adapter.getViewByPosition(detailRc,i, R.id.detail_no_like);
                ImageView isLike = (ImageView) adapter.getViewByPosition(detailRc,i, R.id.detail_is_like);
                switch(view.getId()){
                    case R.id.detail_is_like:
                        initLike(list.get(i).getCid());
                        noLike.setImageResource(R.drawable.post_un_thumb_n);
                        isLike.setImageResource(R.drawable.post_thumb_s);
                        likeNum.setText(Integer.valueOf(likeNum.getText().toString())+1+"");
                        break;
                    case R.id.detail_no_like:
                        initHate(list.get(i).getCid());
                        noLike.setImageResource(R.drawable.post_un_thumb_s);
                        isLike.setImageResource(R.drawable.post_thumb_n);
                        likeNum.setText(Integer.valueOf(likeNum.getText().toString())-1+"");
                        break;
                    case R.id.detail_more:
                        BottomSheetDialog dialog = new BottomSheetDialog(DetailsActivity.this);
                        View view1 = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.dialog_bottom_item, null);
                        View jubao = view1.findViewById(R.id.jubao);
                        View cencel = view1.findViewById(R.id.cencel);
                        jubao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(DetailsActivity.this, JubaoActivity.class);
                                intent.putExtra("uid",list.get(i).getUid());
                                intent.putExtra("touid",list.get(i));
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        });
                        cencel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.setContentView(view1);
                        dialog.show();
                        break;
                }
            }
        });
        detailRc.setAdapter(adapter);
    }

    private void initHate(String cid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type","2");
        map.put("id",cid);
        NetRequest.postFormHeadRequest(UrlManager.CommentLike, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(DetailsActivity.this,"点赞成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }

    private void initLike(String pid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type","1");
        map.put("id",pid);
        NetRequest.postFormHeadRequest(UrlManager.CommentLike, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                Toast.makeText(DetailsActivity.this,"点赞成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }

    @OnClick({R.id.go_friend_detail2,R.id.go_friend_detail,R.id.detail_focus2,R.id.close_video,R.id.detail_head2,R.id.detail_head,R.id.detail_post,R.id.icon_like,R.id.icon_share,R.id.detail_focus,R.id.changeState,R.id.close,R.id.icon_comment})
    public void Click(View view){
        switch (view.getId()){
            case R.id.close_video:
                finish();
                break;
            case R.id.detail_head:
                Intent intent = new Intent(this, UserDetailActivity.class);
                intent.putExtra("UserId",data.getData().getList().getUid());
                startActivity(intent);
                break;
            case R.id.detail_head2:
                Intent intent2 = new Intent(this, UserDetailActivity.class);
                intent2.putExtra("UserId",data.getData().getList().getUid());
                startActivity(intent2);
                break;
            case R.id.detail_post:
                if(Live.getInstance().getUser(this) == null){
                    Intent intent1 = new Intent(this, LoginActivity.class);
                    startActivity(intent1);
                    return;
                }
                initNet();
                break;
            case R.id.icon_like:
                if(Live.getInstance().getUser(this) == null){
                    Intent intent1 = new Intent(this, LoginActivity.class);
                    startActivity(intent1);
                    return;
                }
                initNet2();
                break;
            case R.id.icon_share:
                if(Live.getInstance().getUser(this) == null){
                    Intent intent1 = new Intent(this, LoginActivity.class);
                    startActivity(intent1);
                    return;
                }
                ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText(null, data.getData().getList().getContent());
                clipboard.setPrimaryClip(clipData);
                Toast.makeText(DetailsActivity.this,"复制成功,快去分享吧",Toast.LENGTH_SHORT).show();
                break;
            case R.id.detail_focus:
                if(Live.getInstance().getUser(this) == null){
                    Intent intent1 = new Intent(this, LoginActivity.class);
                    startActivity(intent1);
                    return;
                }
                initFocus();
                break;
            case R.id.detail_focus2:
                if(Live.getInstance().getUser(this) == null){
                    Intent intent1 = new Intent(this, LoginActivity.class);
                    startActivity(intent1);
                    return;
                }
                initFocus2();
                break;
            case R.id.changeState:
                if(hot.getText().toString().equals("最新")){
                    hot.setText("最热");
                    hotIcon.setImageResource(R.drawable.post_sort_hot);
                    initComment("hot","0");
                }else{
                    hot.setText("最新");
                    hotIcon.setImageResource(R.drawable.post_sort_last);
                    initComment("new","0");
                }
                break;
            case R.id.close:
                finish();
                break;
            case R.id.icon_comment:
                if(Live.getInstance().getUser(this) == null){
                    Intent intent1 = new Intent(this, LoginActivity.class);
                    startActivity(intent1);
                    return;
                }
                initCollection();
                collectionBt.setImageResource(R.drawable.post_collect_menu_s);
                break;
            case R.id.go_friend_detail:
                Intent intent1 = new Intent(this,FriendDetailActivity.class);
                intent1.putExtra("id",data.getData().getList().getCate());
                startActivity(intent1);
                break;
            case R.id.go_friend_detail2:
                Intent intent3 = new Intent(this,FriendDetailActivity.class);
                intent3.putExtra("id",data.getData().getList().getCate());
                startActivity(intent3);
                break;
        }
    }
    //收藏
    private void initCollection() {
        if(null == Live.getInstance().getUser(this)){
            Intent intent = new Intent(DetailsActivity.this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        NetRequest.postFormHeadRequest(UrlManager.Collection_Home, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(DetailsActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                commentNum.setText(Integer.valueOf(commentNum.getText().toString())+1+"");
                collectionBt.setClickable(false);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(DetailsActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }

    private void initFocus() {
        if(isFocus){
            checkLogin();
            isFocus = false;
            focus.setBackgroundResource(R.drawable.focus);
            focus.setText("+关注");
            initFocusCencel();
        }else{
            checkLogin();
            isFocus = true;
            focus.setBackgroundResource(R.drawable.isfocus);
            focus.setText("已关注");
            initFocusNet();
        }
    }
    private void initFocus2() {
        if(isFocus){
            checkLogin();
            isFocus = false;
            focus3.setBackgroundResource(R.drawable.focus);
            focus3.setText("+关注");
            initFocusCencel();
        }else{
            checkLogin();
            isFocus = true;
            focus3.setBackgroundResource(R.drawable.isfocus);
            focus3.setText("已关注");
            initFocusNet();
        }
    }

    private void initFocusCencel() {
        HashMap<String, String> map = new HashMap<>();
        map.put("touid",data.getData().getList().getUid());
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(UrlManager.Focus_Cencel, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                Toast.makeText(DetailsActivity.this,"操作成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(DetailsActivity.this,"操作失败",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }

    private void initFocusNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("touid",data.getData().getList().getUid());
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(UrlManager.Focus_User, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                Toast.makeText(DetailsActivity.this,"操作成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(DetailsActivity.this,"操作失败",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }

    //like_press
    private void initNet2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("type","1");
        map.put("id",id);
        NetRequest.postFormRequest(UrlManager.Like, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(DetailsActivity.this,"点赞成功",Toast.LENGTH_SHORT).show();
                likeicon.setImageDrawable(getResources().getDrawable(R.drawable.like_press));
                likeicon.setClickable(false);
                likenum.setText(Integer.valueOf(likenum.getText().toString())+1+"");
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(DetailsActivity.this,"点赞失败",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }

    private void initNet() {
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        if(detailEt.getText().toString().trim().equals("")){
            Toast.makeText(this,"内容不能为空",Toast.LENGTH_SHORT).show();
        }else{
            HashMap<String, String> map = new HashMap<>();
            map.put("vid",id);
            map.put("pid","0");
            map.put("content",detailEt.getText().toString());
            NetRequest.postFormHeadRequest(UrlManager.Detail_Comment_Put, map,Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
                @Override
                public void requestSuccess(String result) throws Exception {
                    Log.e("333",result);
                    Toast.makeText(DetailsActivity.this,"提交评论成功",Toast.LENGTH_SHORT).show();
                    detailEt.setText("");
                }

                @Override
                public void requestFailure(Request request, IOException e) {
                    Toast.makeText(DetailsActivity.this,"提交评论失败",Toast.LENGTH_SHORT).show();
                }
                @Override
                public void TokenFail() {
                    LoginDialog dialog = new LoginDialog(getActivity());
                    dialog.Show();
                }
            });
        }
    }

    @Override
    protected void LoadData() {
        switchBt.setOnCheckListener(new SwitchButton.OnCheckListener() {
            @Override
            public void onCheck(boolean isCheck) {
                if(isCheck){
                    initComment("new","楼主");
                }else{
                    initComment("new","0");
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //home back
        JZVideoPlayer.goOnPlayOnResume();
    }

}
