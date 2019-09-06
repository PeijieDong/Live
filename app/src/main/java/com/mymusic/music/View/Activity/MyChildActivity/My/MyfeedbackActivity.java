package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.CommunityAdviceFragment;
import com.mymusic.music.View.ChildFragment.CommunityReportFragment;
import com.mymusic.music.View.ChildFragment.HistoryFeedBackFragment;
import com.mymusic.music.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyfeedbackActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_myfeedback);
    }

    @Override
    protected void LoadData() {
        List<String> title = new ArrayList<>();
        title.add("意见反馈");
        title.add("投诉建议");
        title.add("我的反馈");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CommunityAdviceFragment());
        fragments.add(new CommunityReportFragment());
        fragments.add(new HistoryFeedBackFragment());
        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),title,fragments));
        tab.setupWithViewPager(viewPager);
    }


    @OnClick({R.id.back})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
