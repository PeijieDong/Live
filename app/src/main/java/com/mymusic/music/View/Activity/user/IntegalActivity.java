package com.mymusic.music.View.Activity.user;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.mymusic.music.DataBean.CommonData;
import com.mymusic.music.DataBean.VipList;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.Util.AppUtil;
import com.mymusic.music.Util.DpPxUtils;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.WebActivity;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.ScroeFragment;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.CommonDataSource;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class IntegalActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.get_Score)
    TextView getScore;
    String integal;
    @BindView(R.id.picker)
    WheelPicker picker;
    @BindView(R.id.sure)
    TextView sure;
    @BindView(R.id.cencel)
    TextView cencel;
    @BindView(R.id.bootom_chose)
    LinearLayout bottomChose;
    private VipList list;

    @Override
    protected void initVariables(Intent intent) {
        integal = intent.getStringExtra("integal");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_integal);
    }

    @Override
    protected void LoadData() {
        score.setText(integal+"分");
//        int i = Integer.valueOf(integal) / 100;
//        getScore.setText("可兑换时长:"+i+"分");
        List<String> tablist = new ArrayList<>();
        List<Fragment> list = new ArrayList<>();
        tablist.add("获取记录");
        tablist.add("消费记录");
        Bundle bundle = new Bundle();
        bundle.putString("position","1");
        ScroeFragment fragment1 = new ScroeFragment();
        fragment1.setArguments(bundle);
        list.add(fragment1);
        Bundle bundle2 = new Bundle();
        bundle2.putString("position","0");
        ScroeFragment fragment2 = new ScroeFragment();
        fragment2.setArguments(bundle2);
        list.add(fragment2);
        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),tablist,list));
        tab.setupWithViewPager(viewPager);
        initVipList();
    }

    @OnClick({R.id.back,R.id.get_Score,R.id.help_iv})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.get_Score:
//                Intent intent = new Intent(this, WebActivity.class);
//                intent.putExtra("url","http://live.shuiqiao.net/users/jifen");
//                intent.putExtra("title","获取攻略");
//                startActivity(intent);
                if(list == null){
                    initVipList();
                    return;
                }
                ArrayList<String> vipList = new ArrayList<>();
                for (VipList.DataBean.ListBean vip : list.getData().getList()){
                    vipList.add("消耗"+vip.getGold() +"金币,兑换"+ vip.getTimes()+"天");
                }
                picker.setData(vipList);
                bottomChose.setVisibility(View.VISIBLE);
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = picker.getCurrentItemPosition();
                        String id = list.getData().getList().get(position).getId();
                        initVip(id);
                        bottomChose.setVisibility(View.GONE);
                    }
                });
                cencel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomChose.setVisibility(View.GONE);
                    }
                });
                break;
            case R.id.help_iv:
                Dialog dialog = new Dialog(IntegalActivity.this,R.style.transparentDialog);
                View view2 = LayoutInflater.from(IntegalActivity.this).inflate(R.layout.video_integal_dialog, null);
                TextView des = view2.findViewById(R.id.dialog_des);
                if(list != null){
                    List<VipList.DataBean.ListBean> beans = this.list.getData().getList();
                    StringBuilder builder = new StringBuilder();
                    builder.append("金币可用于兑换会员卡： \n");
                    for (VipList.DataBean.ListBean bean : beans){
                        builder.append(bean.getGold()).append("币可抵用").append(bean.getTimes()).append("天.");
                    }
                    des.setText(builder.toString());
                }
                view2.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(view2);
                dialog.show();
                break;
        }
    }

    private void initVipList() {
        NetRequest.postFormRequest(UrlManager.VIP_LIST, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                list = GsonUtil.GsonToBean(result, VipList.class);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }

    private void initVip(String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        NetRequest.postFormRequest(UrlManager.VIP_TIME, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                CommonData bean = GsonUtil.GsonToBean(result, CommonData.class);
                ToastUtil.show(IntegalActivity.this,bean.getInfo(),1500);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(IntegalActivity.this,"兌換失败",1500);
            }

            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
}
