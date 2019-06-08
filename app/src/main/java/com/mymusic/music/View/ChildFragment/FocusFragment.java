package com.mymusic.music.View.ChildFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.music.R;
import com.mymusic.music.View.Adapter.FocusRcAdaper;
import com.mymusic.music.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create By mr.mao in 2019/6/2 20:41
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FocusFragment extends BaseFragment {

    @BindView(R.id.focus_Rc)
    RecyclerView focusRc;
    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_focus,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        List<String> list = new ArrayList<>();
        focusRc.setLayoutManager(new LinearLayoutManager(getContext()));
        focusRc.setAdapter(new FocusRcAdaper(R.layout.focus_rc_layout,list));
    }

    @Override
    protected void LoadData() {

    }
}
