package com.mymusic.music.View.Activity.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.UserList;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
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
        listRc.setAdapter(adapter);
    }
}
