package com.mymusic.music.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.FriendAllFragment;
import com.mymusic.music.View.ChildFragment.FriendFindFragment;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create By mr.mao in 2019/5/29 21:31
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendFragment extends BaseFragment {

    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_friend,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        List<String> title = new ArrayList<>();
        title.add("发现");
        title.add("全部");
        List<Fragment> list = new ArrayList<>();
        list.add(new FriendFindFragment());
        list.add(new FriendAllFragment());
        viewPager.setAdapter(new ViewpagerAdapter(getChildFragmentManager(),title,list));
        tabLayout.setupWithViewPager(viewPager);
    }
}
