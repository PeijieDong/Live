package com.mymusic.music.View.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Create By mr.mao in 2019/6/1 16:34
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class ViewpagerAdapter extends FragmentPagerAdapter {

    private List<String> title;
    private List<Fragment> list;
    public ViewpagerAdapter(FragmentManager fm, List<String> title,List<Fragment> list) {
        super(fm);
        this.title = title;
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
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
