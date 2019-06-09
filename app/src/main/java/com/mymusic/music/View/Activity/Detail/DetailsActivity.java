package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mymusic.music.DataBean.DetailData;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.MyGridView;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.HomeGridAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.detail_Rc)
    RecyclerView recyclerView;
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
    @BindView(R.id.likeNum)
    TextView likeNum;
    @BindView(R.id.detail_head)
    CircleImageView detail_head;
    @BindView(R.id.video_play)
    JZVideoPlayerStandard VideoPlay;
    @BindView(R.id.detail_ll)
    LinearLayout detail_ll;




    @Override
    protected void initVariables(Intent intent) {
        String id = intent.getStringExtra("id");
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        NetRequest.getFormRequest(UrlManager.HOME_DETAILS, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                DetailData data = GsonUtil.GsonToBean(result, DetailData.class);
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
                    JZVideoPlayerStandard.SCROLL_AXIS_HORIZONTAL);
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
        likeNum.setText(data.getData().getList().getZan());
        Glide.with(this).load(data.getData().getList().getAvatar()).into(detail_head);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_details);
    }

    @Override
    protected void LoadData() {

    }
}
