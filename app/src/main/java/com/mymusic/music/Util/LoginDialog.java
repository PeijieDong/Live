package com.mymusic.music.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.View.Activity.Login.LoginActivity;

/**
 * Create By mr.mao in 2019/7/8 23:03
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class LoginDialog {
    private Context context;
    private Activity activity;
    private Fragment fragment;

    public LoginDialog(Context context) {
        this.context = context;
    }
    public LoginDialog(Activity activity){
        this.activity = activity;
        context = activity;
    }
    public LoginDialog(Fragment fragment){
        this.fragment = fragment;
        context = fragment.getContext();
    }
    public void Show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("您还未登录！");
        builder.setPositiveButton("现在登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Live.getInstance().clear(context);
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("不了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
