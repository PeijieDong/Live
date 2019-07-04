package com.mymusic.music.View.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.DataBean.Hot;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
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

public class FoundActivity extends BaseActivity implements View.OnKeyListener {


    @BindView(R.id.toFind)
    LinearLayout toFind;
    @BindView(R.id.toCenter)
    TextView toCenter;
    @BindView(R.id.deleteFound)
    ImageView deleteFound;
    @BindView(R.id.userFind)
    EditText userFind;
    @BindView(R.id.flowLayout)
    TagFlowLayout history;
    @BindView(R.id.flowLayout2)
    TagFlowLayout hotFound;
    @BindView(R.id.foundRecycler)
    RecyclerView Rc;
    @BindView(R.id.found)
    LinearLayout found;

    private List<String> list;
    private LayoutInflater inflater;
    private String text;
    private List<String> list2;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_found);
    }

    @Override
    protected void LoadData() {
        userFind.setOnKeyListener(this);
        inflater = getLayoutInflater();
        initHot();
    }

    private void initHot() {
        NetRequest.getFormRequest(UrlManager.Home_Find, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Hot hot = GsonUtil.GsonToBean(result, Hot.class);
                list2 = hot.getData().getList();
                //初始化流式布局
                initFlow();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initFlow() {
        list = new ArrayList<>();
        List<String> data = SharedPrefrenceUtils.getStringList(this, "history");
        if(data != null){
            list = data;
        }
        //加载数据
        this.history.setAdapter(new TagAdapter<String>(list) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) inflater.inflate(R.layout.search_page_flowlayout_tv2, FoundActivity.this.history, false);
                textView.setText(s);
                return textView;
            }
        });
        //点击事件回调
        history.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                return true;
            }
        });

        //加载数据
        hotFound.setAdapter(new TagAdapter<String>(list2) {
            @Override
            public View getView(FlowLayout parent, int position, String dataBean) {
                TextView textView = (TextView) inflater.inflate(R.layout.search_page_flowlayout_tv2, FoundActivity.this.history, false);
                textView.setText(dataBean);
                return textView;
            }
        });
        //点击事件回调
        hotFound.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                initNet(list2.get(position));
                return true;
            }
        });
    }

    //搜索事件
    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_UP) {
            //先隐藏键盘
            ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(getActivity().getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            // 其次再做相应操作
            String inputContent = userFind.getText().toString().trim();
            if (inputContent.equals("")) {

            } else {
                list.add(inputContent);
                SharedPrefrenceUtils.putStringList(this,"history",list);
                initNet(inputContent);
            }
        }
        return false;
    }

    private void initNet(String inputContent) {
        HashMap<String, String> map = new HashMap<>();
        map.put("keyword",inputContent);
        NetRequest.postFormHeadRequest(UrlManager.Home_Find, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Rc.setVisibility(View.VISIBLE);
                found.setVisibility(View.GONE);
                HomeData data = GsonUtil.GsonToBean(result, HomeData.class);
                initRc(data.getData().getList());
        }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initRc(List<HomeData.DataBean.ListBean> list) {
        Rc.setLayoutManager(new LinearLayoutManager(this));
        HomePagerRecyclerViewAdapter adapter = new HomePagerRecyclerViewAdapter(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(FoundActivity.this, DetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.toCenter,R.id.deleteFound})
    public void Onclick(View v){
        switch (v.getId()){
            case R.id.toCenter:
                finish();
                break;
            case R.id.deleteFound:
                SharedPrefrenceUtils.clearn(this,"history");
                initFlow();
                break;
        }
    }


}
