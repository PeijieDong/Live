package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.FriendDetailTOP;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.post.PutVideoActivity;
import com.mymusic.music.View.Activity.post.putContentActivity;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.FriendDetailFragment1;
import com.mymusic.music.View.ChildFragment.FriendDetailFragment2;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
    @BindView(R.id.gonggao)
    LinearLayout gonggao;
    @BindView(R.id.zhiding)
    LinearLayout zhiding;
    @BindView(R.id.zhiding_content)
    TextView zhiding_content;
    @BindView(R.id.gonggao_content)
    TextView gonggao_content;
    @BindView(R.id.floatBt)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.back_random)
    ImageView backRandom;
    @BindView(R.id.guanfang)
    TextView guanfang;
    private String id;
    private FriendDetailTOP bean;
    private Boolean focuslogo = true;


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
                Log.e("33",result);
                bean = GsonUtil.GsonToBean(result, FriendDetailTOP.class);
                Glide.with(FriendDetailActivity.this).load(bean.getData().getList().getIcon()).into(head);
                title.setText(bean.getData().getList().getTitle());
                fans.setText(bean.getData().getList().getGnum()+"粉丝");
                art.setText(bean.getData().getList().getTiezi()+"帖子");
                if(bean.getData().getList().getOfficial().equals("1")){
                    guanfang.setVisibility(View.VISIBLE);
                }
                if(bean.getData().getList().getGonggao() != null){
                    gonggao.setVisibility(View.VISIBLE);
                    gonggao_content.setText(bean.getData().getList().getGonggao().getTitle());
                }
                if(bean.getData().getList().getZhiding() != null){
                    zhiding.setVisibility(View.VISIBLE);
                    zhiding_content.setText(bean.getData().getList().getZhiding().getTitle());
                }
                initView();
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

    private void initView() {
        Random random = new Random();
        int i = random.nextInt(1);
        switch (i){
            case 0:
                backRandom.setBackgroundResource(R.drawable.my_home_page_default);
                break;
            case 1:
                backRandom.setBackgroundResource(R.drawable.my_home_page_member_default);
                break;
        }
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
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Live.getInstance().getUser(FriendDetailActivity.this) == null ){
                    startActivity(new Intent(FriendDetailActivity.this, LoginActivity.class));
                    return;
                }
                if(Integer.parseInt(Live.getInstance().getUser(FriendDetailActivity.this).getData().getLevel())<3){
                    ToastUtil.show(FriendDetailActivity.this,"只有3级以上用户可以使用",Toast.LENGTH_SHORT);
                    return ;
                }
                Intent intent = new Intent(FriendDetailActivity.this, putContentActivity.class);
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.friend_detail_go,R.id.back,R.id.friend_detail_focus,R.id.zhiding,R.id.gonggao})
    public void ClickEvent(View v){
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.friend_detail_go:
                Intent intent = new Intent(this,FriendDetailTwoActivity.class);
                intent.putExtra("frienddetail",bean);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.friend_detail_focus:
                if(Live.getInstance().getUser(this) == null){
                    Intent intent1 = new Intent(this, LoginActivity.class);
                    startActivity(intent1);
                    return;
                }
                focus(focuslogo);
                break;
            case R.id.zhiding:
                Intent intent1 = new Intent(this, DetailsActivity.class);
                intent1.putExtra("id",bean.getData().getList().getZhiding().getId());
                startActivity(intent1);
                break;
            case R.id.gonggao:
                Intent intent2 = new Intent(this, DetailsActivity.class);
                intent2.putExtra("id",bean.getData().getList().getGonggao().getId());
                startActivity(intent2);
                break;
        }
    }


    public void focus(boolean isfocus){
        if(isfocus){
            focus.setText("取消关注");
            focus.setBackgroundResource(R.drawable.back_friend_detail_cencelfocus);
            focuslogo = false;
            initFocusFriend(true);
        }else{
            focus.setText("+关注");
            focus.setBackgroundResource(R.drawable.back_friend_detail_focus);
            focuslogo = true;
            initFocusFriend(false);
        }
    }
    private void initFocusFriend(boolean isFocus) {
        String url = "";
        if(isFocus){
            url = UrlManager.Focus_Friend;
        }else{
            url = UrlManager.NoFocus_Friend;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("touid",bean.getData().getList().getCid());
        NetRequest.postFormHeadRequest(url, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                ToastUtil.show(FriendDetailActivity.this,"操作成功",Toast.LENGTH_SHORT);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(FriendDetailActivity.this,"操作失败",Toast.LENGTH_SHORT);
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
}
