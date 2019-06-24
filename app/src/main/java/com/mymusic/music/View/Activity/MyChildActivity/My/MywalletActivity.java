package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.mymusic.music.DataBean.Wallet;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.MoneyDetailActivity;
import com.mymusic.music.View.Activity.WebActivity;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.WalletFragment;
import com.mymusic.music.View.ChildFragment.WalletOnLineFragment;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class MywalletActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.money_detail)
    TextView money_detail;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mywallet);
    }

    @Override
    protected void LoadData() {
        initNet();

    }

    private void initNet() {
        NetRequest.postFormHeadRequest(UrlManager.Get_Wallet, null, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Wallet bean = GsonUtil.GsonToBean(result, Wallet.class);
                List<String> title = new ArrayList<>();
                title.add("代理充值");
                title.add("在线充值");
                List<Fragment> list = new ArrayList<>();
                Bundle bundle = new Bundle();
                bundle.putSerializable("wallet",bean);
                WalletFragment fragment = new WalletFragment();
                fragment.setArguments(bundle);
                WalletOnLineFragment fragment2 = new WalletOnLineFragment();
                fragment2.setArguments(bundle);
                list.add(fragment);
                list.add(fragment2);
                viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),title,list));
                tab.setupWithViewPager(viewPager);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }
    @OnClick({R.id.money_detail,R.id.helper})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.money_detail:
                Intent intent = new Intent(this, MoneyDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.helper:
                Intent intent1 = new Intent(this, WebActivity.class);
                intent1.putExtra("url","http://live.shuiqiao.net/users/share");
                startActivity(intent1);
                break;
        }
    }
}
