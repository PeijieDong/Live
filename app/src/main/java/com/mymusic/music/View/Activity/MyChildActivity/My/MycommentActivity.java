package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.CommentArtFragment;
import com.mymusic.music.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MycommentActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mycomment);
    }

    @Override
    protected void LoadData() {
        initView();
    }

    private void initView() {
        List<String> title = new ArrayList<>();
        List<Fragment> list = new ArrayList<>();
        title.add("帖子评论");
//        title.add("啪啪评论");
        list.add(new CommentArtFragment());
//        list.add(new CommentPPFragment());
        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),title,list));
        tab.setupWithViewPager(viewPager);
    }
    @OnClick({R.id.back})
    public void Click(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
