package com.mymusic.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Create By mr.mao in 2019/5/28 20:07
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public abstract class BaseFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = CreateView(inflater,container);
            ButterKnife.bind(inflater,view);
            initVariables(getArguments());
            initViews(savedInstanceState);
            LoadData();
            return view;
        }
        return view;
    }

    protected abstract View CreateView(LayoutInflater inflater,ViewGroup container);

    protected abstract void initVariables(Bundle bundle);

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void LoadData();
}
