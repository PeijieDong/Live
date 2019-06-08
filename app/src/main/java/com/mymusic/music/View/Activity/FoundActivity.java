package com.mymusic.music.View.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mymusic.music.R;
import com.mymusic.music.Util.SharedPrefrenceUtils;
import com.mymusic.music.base.BaseActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
        //初始化流式布局
        initFlow();
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
        hotFound.setAdapter(new TagAdapter<String>(list) {
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

            }
        }
        return false;
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
