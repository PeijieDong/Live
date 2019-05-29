package com.mymusic.music.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.music.View.ChildFragment.HomePagerFragment;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create By mr.mao in 2019/5/29 21:29
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_tab)
    TabLayout tabLayout;
    @BindView(R.id.home_vp)
    ViewPager viewPager;
    private List<String> title;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        title = new ArrayList<>();
        title.add("推荐"); title.add("关注"); title.add("图片"); title.add("短文"); title.add("视频");
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class HomePagerAdapter extends FragmentStatePagerAdapter {


        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            HomePagerFragment fragment = new HomePagerFragment();
            return fragment;
        }

        @Override
        public int getCount() {
            return title.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }
}
