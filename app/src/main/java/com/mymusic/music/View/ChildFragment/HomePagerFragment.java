package com.mymusic.music.View.ChildFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create By mr.mao in 2019/5/29 22:19
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class HomePagerFragment extends BaseFragment {

    @BindView(R.id.home_pager_Rc)
    RecyclerView homePagerRecyclerview;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_pager,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        refresh.autoRefresh();
    }

    @Override
    protected void LoadData() {
        List<String> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            list.add("sss");
        }
        homePagerRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        homePagerRecyclerview.setAdapter(new HomePagerRecyclerViewAdapter(R.layout.home_pager_layout,list));
    }
}
