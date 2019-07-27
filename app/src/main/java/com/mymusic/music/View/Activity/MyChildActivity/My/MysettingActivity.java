package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mymusic.music.DataBean.User;
import com.mymusic.music.Live;
import com.mymusic.music.MainActivity;
import com.mymusic.music.R;
import com.mymusic.music.Util.DiyView.SwitchButton;
import com.mymusic.music.Util.LocaleUtils;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.Util.cacheManager;
import com.mymusic.music.View.Activity.Backlist2Activity;
import com.mymusic.music.View.Activity.MyChildActivity.SettingActivity.AccountActivity;
import com.mymusic.music.View.Activity.MyChildActivity.SettingActivity.BacklistActivity;
import com.mymusic.music.View.Activity.MyChildActivity.SettingActivity.LockScreenActivity;
import com.mymusic.music.View.Activity.user.UserActivity;
import com.mymusic.music.base.BaseActivity;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MysettingActivity extends BaseActivity {

    private int choice;
    @BindView(R.id.setting_languageName)
    TextView Language;
    @BindView(R.id.setting_lockScreen)
    SwitchButton lockBt;
    @BindView(R.id.setting_user_head)
    CircleImageView head;
    @BindView(R.id.cache_num)
    TextView cacheNum;
    User user;


    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mysetting);
        try {
            cacheNum.setText(cacheManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        initView();
    }

    private void initView() {
        user = Live.getInstance().get(this);
        Glide.with(this).load(user.getList().getAvatar()).into(head);
    }

    @Override
    protected void LoadData() {
        if(!SharedPrefrenceUtils.getString(this,"Password" ).equals("")){
            lockBt.setClickable(false);
        }else{
            lockBt.setClickable(true);
        }

        initClock();
        //初始化语言选择
        Locale locale = LocaleUtils.getCurrentLocale(this);
        if(locale.equals(Locale.SIMPLIFIED_CHINESE)){
            choice = 0;
            Language.setText(getResources().getString(R.string.setting_language_china));
        }else{
            choice = 1;
            Language.setText(getResources().getString(R.string.setting_language_Taiwan));
        }
    }

    //应用锁
    private void initClock() {
        lockBt.setOnCheckListener(new SwitchButton.OnCheckListener() {
            @Override
            public void onCheck(boolean isCheck) {
                if(isCheck){
                    Intent intent = new Intent(MysettingActivity.this, LockScreenActivity.class);
                    startActivity(intent);
                }else{
                    SharedPrefrenceUtils.clearn(MysettingActivity.this,"Password");
                }
            }
        });
    }

    @OnClick({R.id.back,R.id.setting_information,R.id.setting_accountSecurity,R.id.setting_lockScreen,
            R.id.setting_language,R.id.setting_clearCache,R.id.setting_version,R.id.setting_backlist,
            R.id.setting_aboutOur,R.id.setting_loginBack})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.setting_information:
                goActivity(UserActivity.class);
                break;
            case R.id.setting_accountSecurity:
                goActivity(AccountActivity.class);
                break;
            case R.id.setting_lockScreen:
                goActivity(LockScreenActivity.class);
                break;
            case R.id.setting_language:
                initDialog();
                break;
            case R.id.setting_clearCache:
                cacheManager.cleanInternalCache(this);
                cacheNum.setText("0KB");
                break;
            case R.id.setting_version:

                break;
            case R.id.setting_backlist:
                goActivity(Backlist2Activity.class);
                break;
            case R.id.setting_aboutOur:
                goActivity(MyaboutActivity.class);
                break;
            case R.id.setting_loginBack:
                Live.getInstance().clear(this);
                goActivity(MainActivity.class);
                break;
        }
    }

    private void initDialog() {
        final String[] items = {"简体中文","繁体中文"};
        AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(MysettingActivity.this);
        singleChoiceDialog.setTitle("请选择要切换的语言");
        //第二个参数是默认的选项
        singleChoiceDialog.setSingleChoiceItems(items, choice, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                choice = which;
            }
        });
        singleChoiceDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 1){
                    LocaleUtils.updateLocale(MysettingActivity.this, LocaleUtils.LOCALE_CHINESE);
                    restartAct();
                }else{
                    LocaleUtils.updateLocale(MysettingActivity.this, LocaleUtils.TRADITIONAL_CHINESE);
                    restartAct();
                }
            }
        });
        singleChoiceDialog.show();

    }

    /**
     * 统一跳转
     * @param clazz
     */
    public void goActivity(Class<?> clazz){
        Intent intent = new Intent(MysettingActivity.this, clazz);
        startActivity(intent);
    }

    /**
     * 重启当前Activity
     */
    private void restartAct() {
        finish();
        Intent _Intent = new Intent(this, MainActivity.class);
        startActivity(_Intent);
        //清除Activity退出和进入的动画
        overridePendingTransition(0, 0);
    }
}
