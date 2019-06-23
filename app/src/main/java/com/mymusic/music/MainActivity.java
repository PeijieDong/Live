package com.mymusic.music;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mymusic.music.Util.BottomNavigation;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.View.Activity.MyChildActivity.SettingActivity.LockScreenActivity;
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
    @BindView(R.id.containter)
    FrameLayout containter;
    @BindView(R.id.main_rl)
    RelativeLayout rl;
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
        bottomNavigation.addTab(new BottomNavigation.Tab().setNormalIcon(R.drawable.icon_home_normal)
        .setPressedIcon(R.drawable.icon_home_pressed).setText("首页"));
        bottomNavigation.addTab(new BottomNavigation.Tab().setNormalIcon(R.drawable.icon_video_normal)
                .setPressedIcon(R.drawable.icon_video_pressed).setText("视频"));
        bottomNavigation.addTab(new BottomNavigation.Tab().setNormalIcon(R.drawable.icon_live_normal)
                .setPressedIcon(R.drawable.icon_live_pressed).setText("直播"));
        bottomNavigation.addTab(new BottomNavigation.Tab().setNormalIcon(R.drawable.icon_friend_normal)
                .setPressedIcon(R.drawable.icon_friend_pressed).setText("圈子"));
        bottomNavigation.addTab(new BottomNavigation.Tab().setNormalIcon(R.drawable.icon_my_normal)
                .setPressedIcon(R.drawable.icon_my_pressed).setText("我的"));
        bottomNavigation.setOnTabChechListener(this);
        bottomNavigation.setCurrentItem(0);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onTabSelected(View v, int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) containter.getLayoutParams();
        for(int i = 0;i<list.size();i++){
            if(i == position){
                if(position == 1){
                    params.removeRule(RelativeLayout.ABOVE);
                    bottomNavigation.setBackgroundColor(getResources().getColor(R.color.transparent));
                }else {
                    params.addRule(RelativeLayout.ABOVE, R.id.bottom_navigation);
                    bottomNavigation.setBackgroundColor(getResources().getColor(R.color.white));
                }
                transaction.show(list.get(i));
            }else{
                transaction.hide(list.get(i));
            }
        }
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
