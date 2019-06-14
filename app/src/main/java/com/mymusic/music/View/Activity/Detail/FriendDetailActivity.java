package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.FriendDetailTOP;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.FriendDetailFragment1;
import com.mymusic.music.View.ChildFragment.FriendDetailFragment2;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class FriendDetailActivity extends BaseActivity {

    @BindView(R.id.friend_detail_head)
    ImageView head;
    @BindView(R.id.friend_detail_title)
    TextView title;
    @BindView(R.id.friend_detail_fansnum)
    TextView fans;
    @BindView(R.id.friend_detail_artnum)
    TextView art;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.friend_detail_focus)
    TextView focus;
    private String id;
    private FriendDetailTOP bean;
    private Boolean focuslogo;


    @Override
    protected void initVariables(Intent intent) {
        id = intent.getStringExtra("id");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_friend_detail);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        NetRequest.getFormRequest(UrlManager.FRIEND_DETAILS, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                bean = GsonUtil.GsonToBean(result, FriendDetailTOP.class);
                Glide.with(FriendDetailActivity.this).load(bean.getData().getList().getIcon()).into(head);
                title.setText(bean.getData().getList().getTitle());
                fans.setText(bean.getData().getList().getGnum()+"粉丝");
                art.setText(bean.getData().getList().getTiezi()+"帖子");
                initView();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initView() {
        focus(focuslogo);
        List<String> title = new ArrayList<>();
        List<Fragment> list = new ArrayList<>();
        title.add(getResources().getString(R.string.dynamic));
        title.add(getResources().getString(R.string.video));
        FriendDetailFragment1 fragment1 = new FriendDetailFragment1();
        FriendDetailFragment2 fragment2 = new FriendDetailFragment2();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        fragment1.setArguments(bundle);
        fragment2.setArguments(bundle);
        list.add(fragment1);
        list.add(fragment2);
        tab.setupWithViewPager(viewPager);
        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),title,list));
    }


    @OnClick({R.id.friend_detail_go,R.id.back,R.id.friend_detail_focus})
    public void ClickEvent(View v){
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.friend_detail_go:
                Intent intent = new Intent(this,FriendDetailTwoActivity.class);
                intent.putExtra("frienddetail",bean);
                startActivity(intent);
                break;
            case R.id.friend_detail_focus:
                focus(focuslogo);
                break;
        }
    }


    public void focus(boolean isfocus){
        if(isfocus){
            focus.setText("取消关注");
            focus.setBackgroundResource(R.drawable.back_friend_detail_cencelfocus);
            focuslogo = false;
        }else{
            focus.setText("+关注");
            focus.setBackgroundResource(R.drawable.back_friend_detail_focus);
            focuslogo = true;
        }
    }
}
