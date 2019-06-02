package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.FocusFragment;
import com.mymusic.music.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;

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
        title.add(getResources().getString(R.string.focus_tab2));
        List<Fragment> list = new ArrayList<>();
        list.add(new FocusFragment());
        list.add(new FocusFragment());
        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),title,list));
        tabLayout.setupWithViewPager(viewPager);
    }
}
