package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.Util.AppBarStateChangeListener;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;



public class UserDetailActivity extends BaseActivity {

    @BindView(R.id.userDetail_appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
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
        initView();
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
}
