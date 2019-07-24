package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.UserDetail;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.AppBarStateChangeListener;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.user.UserActivity;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
    @BindView(R.id.userDetail_focus)
    TextView focus;
    @BindView(R.id.back_random)
    ImageView backRandom;
    @BindView(R.id.level_back)
    LinearLayout levelBack;
    @BindView(R.id.level_image)
    ImageView levelImage;
    private String id;
    private boolean isFocus = false;

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
        Random random = new Random();
        int i = random.nextInt(4);
        switch (i){
            case 0:
                backRandom.setBackgroundResource(R.drawable.circle_single_bg_1);
                break;
            case 1:
                backRandom.setBackgroundResource(R.drawable.circle_single_bg_2);
                break;
            case 2:
                backRandom.setBackgroundResource(R.drawable.circle_single_bg_3);
                break;
            case 3:
                backRandom.setBackgroundResource(R.drawable.circle_single_bg_4);
                break;
            case 4:
                backRandom.setBackgroundResource(R.drawable.circle_single_bg_1);
                break;
        }
        if(Live.getInstance().getUser(this) != null){
            if(id.equals(Live.getInstance().getUser(this).getData().getId())){
                editTv.setVisibility(View.VISIBLE);
            }else{
                focus.setVisibility(View.VISIBLE);
            }
        }
        initNet();
        initView();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid",id);
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
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
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
        if(Integer.parseInt(bean.getData().getList().getLevel()) > 5 && Integer.parseInt(bean.getData().getList().getLevel()) < 11){
            level.setTextColor(ContextCompat.getColor(this,R.color.text_level_10));
            levelBack.setBackgroundResource(R.drawable.level_back_10);
            levelImage.setImageResource(R.drawable.icon_user_level_10);
        }
        if(Integer.parseInt(bean.getData().getList().getLevel()) > 10 && Integer.parseInt(bean.getData().getList().getLevel()) < 16){
            level.setTextColor(ContextCompat.getColor(this,R.color.text_level_15));
            levelBack.setBackgroundResource(R.drawable.level_back_15);
            levelImage.setImageResource(R.drawable.icon_user_level_15);
        }
        if(Integer.parseInt(bean.getData().getList().getLevel()) > 15){
            level.setTextColor(ContextCompat.getColor(this,R.color.text_level_20));
            levelBack.setBackgroundResource(R.drawable.level_back_20);
            levelImage.setImageResource(R.drawable.icon_user_level_20);
        }
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
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        List<Fragment> data = new ArrayList<>();
        UserDynamicFragment dynamicFragment = new UserDynamicFragment();
        dynamicFragment.setArguments(bundle);
        data.add(dynamicFragment);
        UserVideoFragment videoFragment = new UserVideoFragment();
        videoFragment.setArguments(bundle);
        data.add(videoFragment);
        UserArchivesFragment archivesFragment = new UserArchivesFragment();
        archivesFragment.setArguments(bundle);
        data.add(archivesFragment);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),list,data));
    }

    @OnClick({R.id.edit_tv,R.id.back,R.id.userDetail_focus})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.edit_tv:
                Intent intent = new Intent(UserDetailActivity.this, UserActivity.class);
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.userDetail_focus:
                initFocus();
                break;
        }
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

    private void initFocusCencel() {
        HashMap<String, String> map = new HashMap<>();
        map.put("touid",id);
        if(Live.getInstance().getUser(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(UrlManager.Focus_Cencel, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                ToastUtil.show(UserDetailActivity.this,"操作成功",Toast.LENGTH_SHORT);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(UserDetailActivity.this,"操作失败",Toast.LENGTH_SHORT);
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
        map.put("touid",id);
        if(Live.getInstance().getUser(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(UrlManager.Focus_User, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                ToastUtil.show(UserDetailActivity.this,"操作成功",Toast.LENGTH_SHORT);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(UserDetailActivity.this,"操作失败",Toast.LENGTH_SHORT);
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
}
