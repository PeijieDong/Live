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

    public void goLogin(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("您还未登录，无法使用该功能，现在登录？");
        builder.setNegativeButton("不了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();
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
