package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.UserDetail;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.AppBarStateChangeListener;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.user.UserActivity;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;


public class UserDetailActivity extends BaseActivity {

    @BindView(R.id.userDetail_appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.head)
    CircleImageView head;
    @BindView(R.id.userDetail_username)
    TextView name;
    @BindView(R.id.fansNum)
    TextView fansNum;
    @BindView(R.id.focusNum)
    TextView focusNum;
    @BindView(R.id.userDetail_des)
    TextView des;
    @BindView(R.id.level)
    TextView level;
    @BindView(R.id.edit_tv)
    TextView editTv;
    private String id;

    @Override
    protected void initVariables(Intent intent) {
        id = intent.getStringExtra("UserId");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user_detail);
    }

    @Override
    protected void LoadData() {
        if(id.equals(Live.getInstance().getUser(this).getData().getId())){
            editTv.setVisibility(View.GONE);
        }
        initNet();
        initView();
    }

    private void initNet() {
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("uid",id);
        Log.e("33",id);
        NetRequest.postFormHeadRequest(UrlManager.User_Detail, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                UserDetail bean = GsonUtil.GsonToBean(result, UserDetail.class);
                initUser(bean);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Log.e("33",e.getMessage());
            }
        });
    }

    private void initUser(UserDetail bean) {
        Glide.with(this).load(bean.getData().getList().getAvatar()).into(head);
        name.setText(bean.getData().getList().getUser_nicename());
        fansNum.setText(bean.getData().getList().getFans());
        focusNum.setText(bean.getData().getList().getFollows());
        des.setText(bean.getData().getList().getSignature());
        level.setText("Lv."+bean.getData().getList().getLevel());
    }

    private void initView() {
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    toolbar.setVisibility(View.GONE);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    toolbar.setVisibility(View.VISIBLE);
                } else {
                    //中间状态
                    toolbar.setVisibility(View.GONE);
                }
            }
        });
        List<String> list = new ArrayList<>();
        list.add(getResources().getString(R.string.dynamic));
        list.add(getResources().getString(R.string.video));
        list.add(getResources().getString(R.string.archives));
        List<Fragment> data = new ArrayList<>();
        data.add(new UserDynamicFragment());
        data.add(new UserVideoFragment());
        data.add(new UserArchivesFragment());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),list,data));
    }

    @OnClick({R.id.edit_tv,R.id.back})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.edit_tv:
                Intent intent = new Intent(UserDetailActivity.this, UserActivity.class);
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
