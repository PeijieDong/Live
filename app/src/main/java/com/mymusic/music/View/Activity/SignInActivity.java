package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mymusic.music.DataBean.SignIn;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.SignInNavigation;
import com.mymusic.music.View.Adapter.SignInAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
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
    @BindView(R.id.signInProgress)
    ProgressBar progressBar;
    @BindView(R.id.rc)
    RecyclerView Rc;
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
        SignInNavigation.Tab tab = new SignInNavigation.Tab().setDayNum("1天").setRewardNum("+1");
        num.addTab(tab);
        SignInNavigation.Tab tab2 = new SignInNavigation.Tab().setDayNum("2天").setRewardNum("+1");
        num.addTab(tab2);
        SignInNavigation.Tab tab3 = new SignInNavigation.Tab().setDayNum("3天").setRewardNum("+1");
        num.addTab(tab3);
        SignInNavigation.Tab tab4 = new SignInNavigation.Tab().setDayNum("4天").setRewardNum("+1");
        num.addTab(tab4);
        SignInNavigation.Tab tab5 = new SignInNavigation.Tab().setDayNum("5天").setRewardNum("+1");
        num.addTab(tab5);
        SignInNavigation.Tab tab6 = new SignInNavigation.Tab().setDayNum("6天").setRewardNum("+5");
        num.addTab(tab6);
        SignInNavigation.Tab tab7 = new SignInNavigation.Tab().setDayNum("7天").setRewardNum("+5");
        num.addTab(tab7);
        initNet();
    }


    private void initNet() {
        NetRequest.postFormRequest(UrlManager.SIGNIN, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("result",result);
                SignIn bean = GsonUtil.GsonToBean(result, SignIn.class);
                getMoney.setText(bean.getData().getMoney()+"元");
                String s = "已签到<font color='#FF0000'>" + bean.getData().getTotaldays() + "</font>天";
                isSignDay.setText(Html.fromHtml(s));
                num.setCurrentItem(bean.getData().getTotaldays());
                progressBar.setMax(7);
                progressBar.setProgress(bean.getData().getTotaldays());
                Rc.setLayoutManager(new LinearLayoutManager(SignInActivity.this));
                Rc.setAdapter(new SignInAdapter(R.layout.get_scroe_item,bean.getData().getList()));
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }
    @OnClick(R.id.back)
    public void ClickEvent(){
        finish();
    }
}
