package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.mymusic.music.DataBean.SignIn;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.SignInNavigation;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Request;

public class SignInActivity extends BaseActivity {

    @BindView(R.id.signIn_num)
    SignInNavigation num;
    @BindView(R.id.get_money)
    TextView getMoney;
    @BindView(R.id.isSignDay)
    TextView isSignDay;
    @BindView(R.id.des)
    TextView des;
    private String desTv = "说明：<font color='#FFE400'>连续签到</font>5天，签到金上升到" +
            "<font color='#FFE400'>5倍</font> 如间断<font color='#FFE400'>漏签</font>，金额<font color='#FFE400'>清零</font>，重新计算";
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sign_in);
    }

    @Override
    protected void LoadData() {
        des.setText(Html.fromHtml(desTv));
        initNet();
    }

    private void initNet() {
        NetRequest.postFormRequest(UrlManager.SIGNIN, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("result",result);
                SignIn bean = GsonUtil.GsonToBean(result, SignIn.class);
                for (int i = 0;i<bean.getData().getList().size() ;i++){
                    SignInNavigation.Tab tab = new SignInNavigation.Tab();
                    num.addTab(tab);
                }
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }
}
