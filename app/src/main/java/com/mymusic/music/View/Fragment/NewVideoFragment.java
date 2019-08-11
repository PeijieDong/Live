package com.mymusic.music.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mymusic.music.DataBean.PinDao;
import com.mymusic.music.DataBean.TopType;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.FindActivity;
import com.mymusic.music.View.Activity.MorePindaoActivity;
import com.mymusic.music.View.ChildFragment.VideoItemFragment;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/7/31 12:59
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class NewVideoFragment extends BaseFragment {

    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.Find)
    LinearLayout find;
    private List<String> tabs;
    private List<Fragment> fragments;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.new_video_layout,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initTab();
    }

    private void initTab() {
        NetRequest.getFormRequest(UrlManager.GET_TOP, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                TopType bean = GsonUtil.GsonToBean(result, TopType.class);
                tabs = new ArrayList<>();
                fragments = new ArrayList<>();
                for (int i = 0 ; i<bean.getData().getList().size();i++){
                    tabs.add(bean.getData().getList().get(i).getTitle());
                    VideoItemFragment fragment = new VideoItemFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("pid",bean.getData().getList().get(i).getId());
                    fragment.setArguments(bundle);
                    fragments.add(fragment);
                }
                TabPagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager());
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }

    @Override
    protected void LoadData() {

    }


    private class TabPagerAdapter extends FragmentStatePagerAdapter {

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }

    @OnClick({R.id.Find,R.id.more})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.Find:
                Intent intent = new Intent(getContext(), FindActivity.class);
                startActivity(intent);
                break;
            case R.id.more:
                Intent intent1 = new Intent(getContext(), MorePindaoActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
