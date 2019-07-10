package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.PutFragment;
import com.mymusic.music.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MypubliskActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mypublisk);
    }

    @Override
    protected void LoadData() {
        List<String> title = new ArrayList<>();
        List<Fragment> list = new ArrayList<>();
        title.add("已发布");
        title.add("待审核");
        Bundle bundle = new Bundle();
        PutFragment fragment = new PutFragment();
        bundle.putInt("position",1);
        fragment.setArguments(bundle);
        list.add(fragment);
        Bundle bundle1 = new Bundle();
        bundle1.putInt("position",2);
        PutFragment fragment1 = new PutFragment();
        fragment1.setArguments(bundle1);
        list.add(fragment1);
        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),title,list));
        tabLayout.setupWithViewPager(viewPager);
    }
    @OnClick({R.id.back})
    public void ClickBack(View view){
        finish();
    }
}
