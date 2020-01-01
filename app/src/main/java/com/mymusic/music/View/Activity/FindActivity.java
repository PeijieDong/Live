package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.Find;
import com.mymusic.music.DataBean.VideoFind;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Adapter.FindRcAdapter;
import com.mymusic.music.View.Adapter.pindaoRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class FindActivity extends BaseActivity {

    @BindView(R.id.titleRc)
    RecyclerView rc;
    @BindView(R.id.rc)
    RecyclerView findRc;
    @BindView(R.id.findText)
    EditText findtv;
    @BindView(R.id.flowLayout)
    TagFlowLayout history;
    @BindView(R.id.history_find)
    RelativeLayout history_rv;
    List<String> titles = new ArrayList<>();
    List<String> list;
    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_find);
    }

    @Override
    protected void LoadData() {
        initTitle();
    }

    private void initTitle() {
        loading();
        NetRequest.getFormRequest(UrlManager.GET_FIND_HOT, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33",result);
                hideloading();

                initFlow();

                Find bean = GsonUtil.GsonToBean(result, Find.class);
                pindaoRcAdapter adapter = new pindaoRcAdapter(R.layout.find_item_layout,bean.getData().getList());
                adapter.setClickListener(new pindaoRcAdapter.ClickItemListener() {
                    @Override
                    public boolean ClickEvent(String title) {
//                        if(titles.size() < 5){
//                            if(titles.contains(title)){
//                                ToastUtil.show(FindActivity.this,"已经存在了",1);
//                                return false;
//                            }
//                            titles.add(title);
//                            StringBuilder builder = new StringBuilder();
//                            for (int i=0;i<titles.size();i++){
//                                if(i == titles.size()-1){
//                                    builder.append(titles.get(i));
//                                }else{
//                                    builder.append(titles.get(i)).append("/");
//                                }
//                            }
//                            findtv.setText(builder.toString());
//                            return true;
//                        }else{
//                            ToastUtil.show(FindActivity.this,"最多不能超过5个",1);
//                            return false;
//                        }
                        return false;
                    }

                    @Override
                    public void Remove(String title) {
//                        titles.remove(title);
//                        StringBuilder builder = new StringBuilder();
//                        for (int i=0 ;i<titles.size();i++){
//                            if(i == titles.size()-1){
//                                builder.append(titles.get(i));
//                            }else{
//                                builder.append(titles.get(i)).append("/");
//                            }
//                        }
//                        findtv.setText(builder.toString());
                    }

                    @Override
                    public void ClickTitle(String title) {
                        initNet(title);
                    }
                });
                rc.setLayoutManager(new LinearLayoutManager(FindActivity.this));
                rc.setAdapter(adapter);

            }

            @Override
            public void requestFailure(Request request, IOException e) {
                hideloading();
            }

            @Override
            public void TokenFail() {
                hideloading();
            }
        });
    }

    @OnClick({R.id.find,R.id.back,R.id.deleteFound})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.find:
                if(findtv.getText().toString().equals("")){
                    ToastUtil.show(this,"请输入搜索内容",1);
                    return;
                }
                initNet(findtv.getText().toString());
//                Intent intent = new Intent(this, FindDetailActivity.class);
//                intent.putExtra("find",findtv.getText().toString());
//                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.deleteFound:
                SharedPrefrenceUtils.clearn(this, "findHistory");
                initFlow();
                break;
        }
    }

    private void initFlow() {
        list = new ArrayList<>();
        List<String> data = SharedPrefrenceUtils.getStringList(FindActivity.this, "findHistory");
        if (data != null) {
            list = data;
        }
        //加载数据
        history.setAdapter(new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater.from(FindActivity.this).inflate(R.layout.search_page_flowlayout_tv4, FindActivity.this.history, false);
                textView.setText(s);
                return textView;
            }
        });
        //点击事件回调
        history.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                initNet(list.get(position));
                return true;
            }
        });
    }

    private void initNet(String s) {
        if (SharedPrefrenceUtils.getStringList(this, "findHistory") != null) {
            List<String> history = SharedPrefrenceUtils.getStringList(this, "findHistory");
            if (!history.contains(s)) {
                list.add(s);
                SharedPrefrenceUtils.putStringList(this, "findHistory", list);
            }
        } else {
            list.add(s);
            SharedPrefrenceUtils.putStringList(this, "findHistory", list);
        }

        rc.setVisibility(View.GONE);
        findRc.setVisibility(View.VISIBLE);
        history_rv.setVisibility(View.GONE);
        loading();
        HashMap<String, String> map = new HashMap<>();
        map.put("keyword",s);
        NetRequest.postFormRequest(UrlManager.GET_FIND, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("33",result);
                hideloading();
                findRc.setLayoutManager(new LinearLayoutManager(FindActivity.this));
                VideoFind item = GsonUtil.GsonToBean(result, VideoFind.class);
                FindRcAdapter adapter = new FindRcAdapter(R.layout.found_rc_layout,item.getData().getList());
                View view = LayoutInflater.from(FindActivity.this).inflate(R.layout.empty_layout, null);
                adapter.setEmptyView(view);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        Intent intent = new Intent(FindActivity.this, DetailsActivity.class);
                        intent.putExtra("new",true);
                        intent.putExtra("id",item.getData().getList().get(i).getId());
                        startActivity(intent);
                    }
                });
                findRc.setAdapter(adapter);

            }

            @Override
            public void requestFailure(Request request, IOException e) {
                hideloading();
            }

            @Override
            public void TokenFail() {
                hideloading();
            }
        });
    }
}
