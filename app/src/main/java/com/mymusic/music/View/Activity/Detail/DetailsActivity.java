package com.mymusic.music.View.Activity.Detail;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mymusic.music.DataBean.CommentData;
import com.mymusic.music.DataBean.DetailData;
import com.mymusic.music.DataBean.Details;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.DiyView.SwitchButton;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.MyGridView;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.DetailCommentRcAdapter;
import com.mymusic.music.View.Adapter.HomeGridAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    JZVideoPlayerStandard VideoPlay;
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
        });
    }


    private void initView(DetailData data) {
        if(data.getData().getList().getType().equals("视频")){
            VideoPlay.setVisibility(View.VISIBLE);
            VideoPlay.setUp(data.getData().getList().getContent(),
                    JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
            Glide.with(this).load(data.getData().getList().getImage()).into(VideoPlay.thumbImageView);
        }else if(data.getData().getList().getType().equals("文字")){
            TextView view = (TextView) LayoutInflater.from(this).inflate(R.layout.detail_text_item, null);
            view.setText(data.getData().getList().getContent());
            detail_ll.addView(view);
        }else if(data.getData().getList().getType().equals("图片")){
            View view = LayoutInflater.from(this).inflate(R.layout.home_rc_pic, null);
            TextView content = view.findViewById(R.id.tv_content);
            MyGridView grid = view.findViewById(R.id.home_rc_grid);
            content.setText(data.getData().getList().getContent());
            List<String> list = new ArrayList<>();
            list.add(data.getData().getList().getImage());
            HomeGridAdapter adapter = new HomeGridAdapter(this,list);
            grid.setAdapter(adapter);
            detail_ll.addView(view);
        }
        detail_time.setText(data.getData().getList().getCreatetime());
        home_rc_type.setText(data.getData().getList().getType());
        shareNum.setText(data.getData().getList().getShare());
        commentNum.setText(data.getData().getList().getComment());
        likenum.setText(data.getData().getList().getZan());
        Glide.with(this).load(data.getData().getList().getAvatar()).into(detail_head);
        initComment("new","0");
    }

    private void initComment(String type,String somebody) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("sortby",type);
        map.put("uid",somebody);
        NetRequest.getFormRequest(UrlManager.Detail_Comment, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                list.clear();
                CommentData bean = GsonUtil.GsonToBean(result, CommentData.class);
                list = bean.getData().getList();
                initCommentList(bean.getData().getList());
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initCommentList(List<CommentData.DataBean.ListBean> list) {
        detailRc.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DetailCommentRcAdapter(R.layout.detail_item_layout,list);
        adapter.notifyDataSetChanged();
        detailRc.setAdapter(adapter);
    }

    @OnClick({R.id.detail_post,R.id.icon_like,R.id.icon_share,R.id.detail_focus,R.id.changeState,R.id.back})
    public void Click(View view){
        switch (view.getId()){
            case R.id.detail_post:
                initNet();
                break;
            case R.id.icon_like:
                initNet2();
                break;
            case R.id.icon_share:
                ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText(null, list.get(0).getContent());
                clipboard.setPrimaryClip(clipData);
                Toast.makeText(DetailsActivity.this,"复制成功,快去分享吧",Toast.LENGTH_SHORT).show();
                break;
            case R.id.detail_focus:
                initFocus();
                break;
            case R.id.changeState:
                if(hot.getText().toString().equals("最新")){
                    hot.setText("最热");
                    initComment("hot","0");
                }else{
                    hot.setText("最新");
                    initComment("new","0");
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void initFocus() {
        if(isFocus){
            isFocus = false;
            focus.setBackgroundResource(R.drawable.focus);
            focus.setText("+关注");
        }else{
            isFocus = true;
            focus.setBackgroundResource(R.drawable.isfocus);
            focus.setText("已关注");
        }
    }

    //点赞
    private void initNet2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("type","1");
        map.put("id",id);
        NetRequest.postFormRequest(UrlManager.Like, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(DetailsActivity.this,"点赞成功",Toast.LENGTH_SHORT).show();
                likeicon.setBackground(getResources().getDrawable(R.drawable.ic_launcher_background));
                likeicon.setClickable(false);
                likenum.setText(Integer.valueOf(likenum.getText().toString())+1+"");
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(DetailsActivity.this,"点赞失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initNet() {
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
}
