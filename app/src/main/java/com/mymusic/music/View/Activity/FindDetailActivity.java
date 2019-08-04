package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mymusic.music.DataBean.PinDao;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.FindDetailItemFragment;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class FindDetailActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    String find;
    @Override
    protected void initVariables(Intent intent) {
        find = intent.getStringExtra("find");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_find_detail);
    }

    @Override
    protected void LoadData() {
        initTitle();

    }

    private void initTitle() {
        NetRequest.getFormRequest(UrlManager.GET_PINDAO, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                PinDao bean = GsonUtil.GsonToBean(result, PinDao.class);
                List<Fragment> list = new ArrayList<>();
                List<String> title = new ArrayList<>();
                for (int i = 0 ;i <bean.getData().getList().size();i++){
                    FindDetailItemFragment fragment = new FindDetailItemFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("id",bean.getData().getList().get(i).getPid());
                    fragment.setArguments(bundle);
                    list.add(fragment);
                    title.add(bean.getData().getList().get(i).getTitle());
                }
                viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),title,list));
                tab.setupWithViewPager(viewPager);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }

    @OnClick({R.id.back})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
