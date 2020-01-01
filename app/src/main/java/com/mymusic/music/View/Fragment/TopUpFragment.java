package com.mymusic.music.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mymusic.music.DataBean.Money;
import com.mymusic.music.DataBean.Wallet;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.MoneyDetailActivity;
import com.mymusic.music.View.Activity.MyChildActivity.My.MywalletActivity;
import com.mymusic.music.View.Activity.WalletFeedback;
import com.mymusic.music.View.Activity.WebActivity;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.TopVipFragment;
import com.mymusic.music.View.ChildFragment.WalletFragment;
import com.mymusic.music.View.ChildFragment.WalletOnLineFragment;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Create By MR.D
 * 2019/8/16
 * USE:
 **/
public class TopUpFragment extends BaseFragment {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.money_detail)
    TextView money_detail;
    @BindView(R.id.wallet_balance_num)
    TextView money_balance;
    @BindView(R.id.go_money)
    TextView goMoney;
    @BindView(R.id.card_vip)
    CardView cardVip;
    @BindView(R.id.vip_date)
    TextView vipDate;
    private int fragmentID=0;
    private String overtime= "未登录";

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.activity_mywallet,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        initNet();
    }

    WalletFragment fragment;
    WalletOnLineFragment fragment2;
    private void initNet() {
//        if(Live.getInstance().getToken(getContext()) == null){
//            Intent intent = new Intent(getContext(), LoginActivity.class);
//            startActivity(intent);
//            return;
//        }

        if(overtime != null){
            vipDate.setText(overtime);
        }
        NetRequest.postFormHeadRequest(UrlManager.Get_Wallet, null, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("44",result);
                Wallet bean = GsonUtil.GsonToBean(result, Wallet.class);
                money_balance.setText(bean.getData().getList().getMoney());
                List<String> title = new ArrayList<>();
                title.add("代理充值");
                title.add("在线充值");
                title.add("开通VIP");
                List<Fragment> list = new ArrayList<>();
                Bundle bundle = new Bundle();
                bundle.putSerializable("wallet",bean);
                fragment = new WalletFragment();
                fragment.setArguments(bundle);
                fragment2 = new WalletOnLineFragment();
                fragment2.setArguments(bundle);
                TopVipFragment fragment3 = new TopVipFragment();
                list.add(fragment);
                list.add(fragment2);
                list.add(fragment3);
                ViewpagerAdapter adapter = new ViewpagerAdapter(getChildFragmentManager(), title, list);
                viewPager.setAdapter(adapter);
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {

                    }

                    @Override
                    public void onPageSelected(int i) {
                        fragmentID = i;
                        if(i == 2){
                            cardVip.setVisibility(View.GONE);
                        }else{
                            cardVip.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                });
                tab.setupWithViewPager(viewPager);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
    @OnClick({R.id.money_detail,R.id.helper,R.id.fankui,R.id.go_money})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.money_detail:
                if(Live.getInstance().getToken(getContext()).equals("")){
                    Intent intent3 = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent3);
                    return;
                }
                Intent intent = new Intent(getContext(), MoneyDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.helper:
                Intent intent1 = new Intent(getContext(), WebActivity.class);
                intent1.putExtra("url","http://live.shuiqiao.net/users/help");
                intent1.putExtra("title","充值帮助");
                startActivity(intent1);
                break;
            case R.id.fankui:
                if(Live.getInstance().getToken(getContext()).equals("")){
                    Intent intent3 = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent3);
                    return;
                }
                Intent intent2 = new Intent(getContext(), WalletFeedback.class);
                startActivity(intent2);
                break;
            case R.id.go_money:
                if(Live.getInstance().getToken(getContext()).equals("")){
                    Intent intent3 = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent3);
                }else {
                    initMoney();
                }
                break;
        }
    }

    private void initMoney() {
        if(fragmentID == 0){
            if(fragment != null){
                fragment.inittopup();
            }
        }else if(fragmentID == 1){
            if(fragment2 != null){
                fragment2.inittopup();
            }
        }

//        HashMap<String, String> map = new HashMap<>();
//        map.put("money","100");
//        map.put("coin","1000");
//        map.put("type","1");
//        NetRequest.postFormHeadRequest(UrlManager.Money, map, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
//            @Override
//            public void requestSuccess(String result) throws Exception {
//                Money money = GsonUtil.GsonToBean(result, Money.class);
//                Intent intent1 = new Intent(getContext(), WebActivity.class);
//                intent1.putExtra("url",money.getData().getUrl());
//                intent1.putExtra("title","充值");
//                startActivity(intent1);
//            }
//
//            @Override
//            public void requestFailure(Request request, IOException e) {
//
//            }
//            @Override
//            public void TokenFail() {
//                LoginDialog dialog = new LoginDialog(getActivity());
//                dialog.Show();
//            }V
//        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            if(Live.getInstance().getUser(getContext()) != null){
                overtime = Live.getInstance().getUser(getContext()).getData().getOvertime();
                if(overtime != null){
                    vipDate.setText(overtime);
                }
            }
        }
    }
}
