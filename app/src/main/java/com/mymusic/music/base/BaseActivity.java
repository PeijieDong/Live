package com.mymusic.music.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.LoadingDialog;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.MyChildActivity.SettingActivity.LockScreenActivity;

import butterknife.ButterKnife;
import scut.carson_ho.kawaii_loadingview.Kawaii_LoadingView;

/**
 * Create By mr.mao in 2019/5/28 19:35
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressBar loading;
    private Dialog builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(null != getIntent()){
            initVariables(getIntent());
        }else{
            initVariables(null);
        }
        ActivityCollector.addActivity(this);
        initViews(savedInstanceState);
        LoadData();
    }

    protected abstract void initVariables(Intent intent);

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void LoadData();

    public BaseActivity getActivity(){
        return this;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    ProgressDialog progressDialog;
    public void showLoading(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("正在努力上传");
        progressDialog.setMessage("等待中");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

    }
    LoadingDialog dialog;
    public void loading(){
        LoadingDialog.Builder builder = new LoadingDialog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }

    public void hideloading(){
        if(dialog != null){
            dialog.dismiss();
        }
    }

    public void closeLoading(){
        progressDialog.dismiss();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public void checkLogin(){

    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
