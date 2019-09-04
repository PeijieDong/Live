package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.FocusFragment;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyfocusActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_myfouces);
    }

    @Override
    protected void LoadData() {
        List<String> title = new ArrayList<>();
        title.add(getResources().getString(R.string.focus_tab1));
//        title.add(getResources().getString(R.string.focus_tab2));
        List<Fragment> list = new ArrayList<>();
        FocusFragment fragment = new FocusFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",UrlManager.Focus_Person);
        fragment.setArguments(bundle);
        FocusFragment fragment2 = new FocusFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("url",UrlManager.Focus_List);
        fragment2.setArguments(bundle2);
        list.add(fragment);
//        list.add(fragment2);
        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),title,list));
        tabLayout.setupWithViewPager(viewPager);
    }

    @OnClick({R.id.back})
    public void ClickBack(View view){
        finish();
    }
}
