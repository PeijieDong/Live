package com.mymusic.music.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.mymusic.music.Util.LoadingDialog;

import butterknife.ButterKnife;

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
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
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

    protected ProgressDialog progressDialog;
    public void showLoading(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("正在努力上传");
        progressDialog.setMessage("等待中");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }
    LoadingDialog dialog;
    public void loading(){
        LoadingDialog.Builder builder = new LoadingDialog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(true);
        dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void hideloading(){
        if(dialog != null){
            dialog.dismiss();
            dialog.cancel();
        }
    }

    public void closeLoading(){
        progressDialog.dismiss();
        progressDialog.cancel();
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
