package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.FriendAllData;
import com.mymusic.music.DataBean.FriendAllTitle;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LeftNavigation;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Detail.FriendDetailActivity;
import com.mymusic.music.View.Adapter.FriendFoundRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class FriendFoundActivity extends BaseActivity {

    @BindView(R.id.leftNavigation)
    LeftNavigation navigation;
    @BindView(R.id.friend_found_rc)
    RecyclerView rc;
    @BindView(R.id.userFind)
    EditText userFind;
    @BindView(R.id.rc)
    RecyclerView Rc;
    FriendAllTitle title;
    boolean chose;
    @Override
    protected void initVariables(Intent intent) {
        chose = intent.getBooleanExtra("chose", false);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_friend_found);
    }

    @Override
    protected void LoadData() {
        initTitle();
        initEdit();
    }

    private void initEdit() {
        userFind.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    rc.setVisibility(View.GONE);
                    navigation.setVisibility(View.GONE);
                    Rc.setVisibility(View.VISIBLE);
                    initFind();
                    return true;
                }
                return false;
            }
        });
    }

    private void initFind() {
        checkLogin();
        if(userFind.getText().toString().equals("")){
            ToastUtil.show(this,"不能为空",Toast.LENGTH_SHORT);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("keywords",userFind.getText().toString());
        loading();
        NetRequest.postFormHeadRequest(UrlManager.Friend_Find, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                FriendAllData data = GsonUtil.GsonToBean(result, FriendAllData.class);
                initFindRc(data.getData().getList());
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
        hideloading();
    }

    private void initFindRc(List<FriendAllData.DataBean.ListBeanX> list) {
        Rc.setLayoutManager(new LinearLayoutManager(this));
        FriendFoundRcAdapter adapter = new FriendFoundRcAdapter(R.layout.friend_found_item,list);
        adapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_layout,null));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if(chose){
                    Intent intent = new Intent();
                    intent.putExtra("cid",list.get(i).getCid());
                    intent.putExtra("title",list.get(i).getTitle());
                    setResult(300,intent);
                    finish();
                }else {
                    Intent intent = new Intent(FriendFoundActivity.this, FriendDetailActivity.class);
                    intent.putExtra("id",list.get(i).getCid());
                    startActivity(intent);
                }
            }
        });
        Rc.setAdapter(adapter);
    }

    private void initTitle() {
        loading();
        NetRequest.getFormRequest(UrlManager.FRIEND_ALL, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {

                title = GsonUtil.GsonToBean(result, FriendAllTitle.class);
                for (int i = 0;i<title.getData().getList().size();i++){
                    navigation.addTab(new LeftNavigation.Tab().setText(title.getData().getList().get(i)));
                }
                navigation.setCurrentItem(0);
                initNet();
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
        hideloading();
        navigation.setOnTabChechListener(new LeftNavigation.OnTabCheckListener() {
            @Override
            public void onTabSelected(View v, int position) {
                initNet();
            }
        });
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",title.getData().getList().get(navigation.getPosition()));
        loading();
        NetRequest.postFormRequest(UrlManager.FRIEND_ALL, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                FriendAllData data = GsonUtil.GsonToBean(result, FriendAllData.class);
                initRc(data.getData().getList());
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
        hideloading();
    }

    private void initRc(List<FriendAllData.DataBean.ListBeanX> list) {
        rc.setLayoutManager(new LinearLayoutManager(this));
        FriendFoundRcAdapter adapter = new FriendFoundRcAdapter(R.layout.friend_found_item,list);
        adapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_layout,null));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if(chose){
                    Intent intent = new Intent();
                    intent.putExtra("cid",list.get(i).getCid());
                    intent.putExtra("title",list.get(i).getTitle());
                    setResult(300,intent);
                    finish();
                }else {
                    Intent intent = new Intent(FriendFoundActivity.this, FriendDetailActivity.class);
                    intent.putExtra("id",list.get(i).getCid());
                    startActivity(intent);
                }
            }
        });
        rc.setAdapter(adapter);
    }
    @OnClick({R.id.toCenter})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.toCenter:
                finish();
                break;
        }
    }
}
