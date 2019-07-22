package com.mymusic.music.View.Activity.MyChildActivity.My;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.Fans;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.FansRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class MyfansActivity extends BaseActivity {

    @BindView(R.id.fans_Rc)
    RecyclerView rc;
    @BindView(R.id.fans_num)
    TextView num;
    private String id;
    private Fans fans;

    @Override
    protected void initVariables(Intent intent) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_myfans);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(UrlManager.GetFans, null, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                fans = GsonUtil.GsonToBean(result, Fans.class);
                num.setText("您总共有"+fans.getData().getTotal()+"个粉丝");
                initView(fans);
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

    private void initView(Fans fans) {
        rc.setLayoutManager(new LinearLayoutManager(this));
        FansRcAdapter adapter = new FansRcAdapter(R.layout.focus_rc_layout,fans.getData().getList());
        adapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_layout,null));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MyfansActivity.this, UserDetailActivity.class);
                intent.putExtra("UserId",fans.getData().getList().get(position).getUid());
                startActivity(intent);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TextView focus = view.findViewById(R.id.focus_rc_focusbt);
                if(focus.getText().toString().equals("+关注")){
                    focus.setText("取消关注");
                    focus.setBackgroundResource(R.drawable.back_friend_detail_cencelfocus);
                    initFocus(true,i);
                }else{
                    focus.setText("+关注");
                    focus.setBackgroundResource(R.drawable.back_friend_detail_focus);
                    initFocus(false,i);
                }

            }
        });
        rc.setAdapter(adapter);
    }

    private void initFocus(boolean isFocus, int i) {
        String url = "";
        if(isFocus){
            url = UrlManager.Focus_User;
        }else{
            url = UrlManager.NoFocus_User;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("touid",fans.getData().getList().get(i).getUid());
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(url, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                ToastUtil.show(MyfansActivity.this,"操作成功",Toast.LENGTH_SHORT);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(MyfansActivity.this,"操作失败",Toast.LENGTH_SHORT);
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
    @OnClick({R.id.back})
    public void ClickBack(View view){
        finish();
    }
}
