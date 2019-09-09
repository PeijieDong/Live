package com.mymusic.music.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.music.Live;
import com.mymusic.music.Util.LoadingDialog;
import com.mymusic.music.View.Activity.Login.LoginActivity;

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
            ButterKnife.bind(this,view);
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

    public void checkLogin(){
        if(Live.getInstance().getToken(getContext()) == null){
            startActivity(new Intent(getContext(),LoginActivity.class));
            return;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    LoadingDialog dialog;
    public void loading(){
        LoadingDialog.Builder builder = new LoadingDialog.Builder(getContext())
                .setMessage("加载中...")
                .setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }

    public void hideloading(){
        dialog.dismiss();
    }


    ProgressDialog progressDialog;
    public void showLoading(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("正在努力上传");
        progressDialog.setMessage("等待中");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.show();

    }
    public void closeLoading(){
        progressDialog.dismiss();
    }
}
