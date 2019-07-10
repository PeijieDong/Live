package com.mymusic.music.View.Activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.UserList;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.ListUserRcAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Request;

public class ListUserActivity extends BaseActivity {

    @BindView(R.id.list_rc)
    RecyclerView listRc;
    private String id;

    @Override
    protected void initVariables(Intent intent) {
        id = intent.getStringExtra("FriendId");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list_user);
    }

    @Override
    protected void LoadData() {
        initNet();

    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        NetRequest.postFormRequest(UrlManager.User_List, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                UserList list = GsonUtil.GsonToBean(result, UserList.class);
                initRc(list);
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

    private void initRc(UserList list) {
        listRc.setLayoutManager(new LinearLayoutManager(this));
        ListUserRcAdapter adapter = new ListUserRcAdapter(R.layout.focus_rc_layout,list.getData().getList());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ListUserActivity.this, UserDetailActivity.class);
                intent.putExtra("UserId",list.getData().getList().get(position).getUid());
                startActivity(intent);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView focusbt = (TextView) adapter.getViewByPosition(R.id.focus_rc_focusbt, position);
                switch (view.getId()){
                    case R.id.focus:
                        if(Live.getInstance().getUser(ListUserActivity.this) == null){
                            Intent intent1 = new Intent(ListUserActivity.this, LoginActivity.class);
                            startActivity(intent1);
                            return;
                        }
                        if(focusbt.getText().toString().equals("取消关注")){
                            focusbt.setText("+关注");
                            focusbt.setBackgroundResource(R.drawable.focus);
                            initFocusFriend(false,position,list);
                        }else{
                            focusbt.setText("取消关注");
                            focusbt.setBackgroundResource(R.drawable.isfocus);
                            initFocusFriend(true,position,list);
                        }
                        break;
                }
            }
        });
        listRc.setAdapter(adapter);
    }

    private void initFocusFriend(boolean isFocus, int i, UserList list) {
        String url = "";
        if(isFocus){
            url = UrlManager.Focus_User;
        }else{
            url = UrlManager.Focus_Cencel;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("touid",list.getData().getList().get(i).getUid());
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(url, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                Toast.makeText(ListUserActivity.this,"操作成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(ListUserActivity.this,"操作失败",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
}
