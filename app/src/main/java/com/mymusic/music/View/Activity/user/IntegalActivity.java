package com.mymusic.music.View.Activity.user;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.View.Activity.WebActivity;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.ScroeFragment;
import com.mymusic.music.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class IntegalActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.score)
    TextView score;
    String integal;

    @Override
    protected void initVariables(Intent intent) {
        integal = intent.getStringExtra("integal");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_integal);
    }

    @Override
    protected void LoadData() {
        score.setText(integal);
        List<String> tablist = new ArrayList<>();
        List<Fragment> list = new ArrayList<>();
        tablist.add("获取记录");
        tablist.add("消费记录");
        Bundle bundle = new Bundle();
        bundle.putString("position","1");
        ScroeFragment fragment1 = new ScroeFragment();
        fragment1.setArguments(bundle);
        list.add(fragment1);
        Bundle bundle2 = new Bundle();
        bundle2.putString("position","0");
        ScroeFragment fragment2 = new ScroeFragment();
        fragment2.setArguments(bundle2);
        list.add(fragment2);
        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),tablist,list));
        tab.setupWithViewPager(viewPager);
    }

    @OnClick({R.id.back,R.id.get_Score})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.get_Score:
                Intent intent = new Intent(this, WebActivity.class);
                intent.putExtra("url","http://live.shuiqiao.net/users/jifen");
                intent.putExtra("title","获取攻略");
                startActivity(intent);
                break;
        }
    }
}
