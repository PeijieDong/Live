package com.mymusic.music.View.Activity.post;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mymusic.music.Callback.TagFlowListener;
import com.mymusic.music.DataBean.SignTag;
import com.mymusic.music.DiyTab.TabLayout;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.ViewpagerAdapter;
import com.mymusic.music.View.ChildFragment.ChoseFragment;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class SignActivity extends BaseActivity implements TagFlowListener {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.chose_des)
    TextView des;
    @BindView(R.id.flowLayout)
    TagFlowLayout flowLayout;
    private SignTag bean;
    List<String> list = new ArrayList<>();
    private TagFlowListener listener;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sign);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        NetRequest.getFormRequest(UrlManager.GetTag, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                bean = GsonUtil.GsonToBean(result, SignTag.class);
                initTab(bean.getList());
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

    private void initTab(List<SignTag.ListBeanX> list) {
        List<String> title = new ArrayList<>();
        List<Fragment> fragmentlist = new ArrayList<>();
        for (int i = 0;i<list.size();i++){
            title.add(list.get(i).getName());
            ChoseFragment fragment = new ChoseFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("sign",bean);
            bundle.putInt("position",i);
            fragment.setArguments(bundle);
            fragmentlist.add(fragment);
        }
        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(),title,fragmentlist));
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void Click(String list2) {
        if(list.size()>=6){
            Toast.makeText(this,"最多选择6个标签",Toast.LENGTH_SHORT).show();
            return;
        }else {
            list.add(list2);
        }
        if(list.size() == 0){
            des.setVisibility(View.VISIBLE);
            flowLayout.setVisibility(View.GONE);
        }else{
            des.setVisibility(View.GONE);
            flowLayout.setVisibility(View.VISIBLE);
            initFlow();
        }
    }
    public void initFlow(){
        flowLayout.setAdapter(new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater.from(SignActivity.this).inflate(R.layout.search_page_flowlayout_tv3,null);
                textView.setText(s);
                return textView;
            }
        });
    }

    @Override
    public void AcClick(String s) {
        for (int i = 0;i<list.size();i++){
            if(list.get(i).equals(s)){
                list.remove(s);
                initFlow();
            }
        }
        if(list.size() == 0){
            des.setVisibility(View.VISIBLE);
            flowLayout.setVisibility(View.GONE);
        }else{
            des.setVisibility(View.GONE);
            flowLayout.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.back})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                Intent intent = new Intent();
                intent.putExtra("data", list.size());
                intent.putStringArrayListExtra("tag", (ArrayList<String>) list);
                setResult(100,intent);
                this.finish();
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent();
        intent.putExtra("data", list.size());
        intent.putStringArrayListExtra("tag", (ArrayList<String>) list);
        setResult(100,intent);
        this.finish();
        return true;
    }
}
