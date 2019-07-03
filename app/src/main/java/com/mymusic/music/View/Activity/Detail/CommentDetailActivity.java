package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.CommentData;
import com.mymusic.music.DataBean.CommentDetail;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.CommentDetailAdapter;
import com.mymusic.music.View.Adapter.DetailCommentRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;

public class CommentDetailActivity extends BaseActivity {

    @BindView(R.id.detail_head_cir)
    CircleImageView head;
    @BindView(R.id.detail_name)
    TextView name;
    @BindView(R.id.detail_time)
    TextView time;
    @BindView(R.id.detail_content)
    TextView content;
    @BindView(R.id.Rc)
    RecyclerView rc;
    @BindView(R.id.detail_et)
    EditText postEt;
    private CommentData.DataBean.ListBean list;

    @Override
    protected void initVariables(Intent intent) {
        list = (CommentData.DataBean.ListBean) intent.getSerializableExtra("commentList");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_comment_detail);
    }

    @Override
    protected void LoadData() {
        name.setText(list.getUser_nicename());
        time.setText(list.getCreatetime());
        content.setText(list.getContent());
        Glide.with(this).load(list.getAvatar()).into(head);
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",list.getVid());
        Log.e("33",list.getPid());
        NetRequest.postFormRequest(UrlManager.Comment_List, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                CommentData data = GsonUtil.GsonToBean(result, CommentData.class);
                rc.setLayoutManager(new LinearLayoutManager(CommentDetailActivity.this));
                DetailCommentRcAdapter adapter = new DetailCommentRcAdapter(R.layout.detail_item_layout, data.getData().getList());
                if(1 == 1){
                    View view = LayoutInflater.from(CommentDetailActivity.this).inflate(R.layout.null_comment, null);
                    adapter.addFooterView(view);
                }
                rc.setAdapter(adapter);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    @OnClick({R.id.detail_post,R.id.back})
    public void Click(View view){
        switch (view.getId()){
            case R.id.detail_post:
                if(postEt.getText().toString().equals("")){
                    Toast.makeText(CommentDetailActivity.this,"不可以是空的哦",Toast.LENGTH_SHORT).show();
                    return;
                }
                initPost();
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void initPost() {
        HashMap<String, String> map = new HashMap<>();
        map.put("vid",list.getVid());
        map.put("pid",list.getPid());
        map.put("content",postEt.getText().toString());
        NetRequest.postFormHeadRequest(UrlManager.Detail_Comment_Put, map,Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("333",result);
                Toast.makeText(CommentDetailActivity.this,"提交评论成功",Toast.LENGTH_SHORT).show();
                postEt.setText("");
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(CommentDetailActivity.this,"提交评论失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
