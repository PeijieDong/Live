package com.mymusic.music.View.ChildFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.music.R;
import com.mymusic.music.base.BaseFragment;

import butterknife.BindView;

/**
 * Create By mr.mao in 2019/7/31 13:17
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoItemFragment extends BaseFragment {

    @BindView(R.id.Rc)
    RecyclerView rc;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.item_video_fragment_layout,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        rc.setLayoutManager(new LinearLayoutManager(getContext()));
//        rc.setAdapter();
    }
}
