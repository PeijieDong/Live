package com.mymusic.music;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.mymusic.music.Util.BottomNavigation;
import com.mymusic.music.View.Fragment.FriendFragment;
import com.mymusic.music.View.Fragment.HomeFragment;
import com.mymusic.music.View.Fragment.LiveFragment;
import com.mymusic.music.View.Fragment.MyFragment;
import com.mymusic.music.View.Fragment.VideoFragment;
import com.mymusic.music.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *Create By mr.mao in 2019/5/28
 *QQ 583723781 情之初，莫相忘
 *一叶扁舟轻帆卷，暂泊楚江南岸。
**/
public class MainActivity extends BaseActivity implements BottomNavigation.OnTabCheckListener {

    @BindView(R.id.bottom_navigation)
    BottomNavigation bottomNavigation;
    private List<Fragment> list;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void LoadData() {
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new VideoFragment());
        list.add(new LiveFragment());
        list.add(new FriendFragment());
        list.add(new MyFragment());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for(int i=0;i<list.size();i++){
            transaction.add(R.id.containter,list.get(i));
        }
        transaction.commit();
        bottomNavigation.addTab(new BottomNavigation.Tab().setNormalIcon(R.drawable.ic_launcher_background)
        .setPressedIcon(R.drawable.ic_launcher_background).setText("首页"));
        bottomNavigation.addTab(new BottomNavigation.Tab().setNormalIcon(R.drawable.ic_launcher_background)
                .setPressedIcon(R.drawable.ic_launcher_background).setText("视频"));
        bottomNavigation.addTab(new BottomNavigation.Tab().setNormalIcon(R.drawable.ic_launcher_background)
                .setPressedIcon(R.drawable.ic_launcher_background).setText("直播"));
        bottomNavigation.addTab(new BottomNavigation.Tab().setNormalIcon(R.drawable.ic_launcher_background)
                .setPressedIcon(R.drawable.ic_launcher_background).setText("圈子"));
        bottomNavigation.addTab(new BottomNavigation.Tab().setNormalIcon(R.drawable.ic_launcher_background)
                .setPressedIcon(R.drawable.ic_launcher_background).setText("我的"));
        bottomNavigation.setOnTabChechListener(this);
        bottomNavigation.setCurrentItem(0);
    }

    @Override
    public void onTabSelected(View v, int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for(int i = 0;i<list.size();i++){
            if(i == position){
                transaction.show(list.get(position));
            }else{
                transaction.hide(list.get(position));
            }
        }
        transaction.commit();
    }
}
