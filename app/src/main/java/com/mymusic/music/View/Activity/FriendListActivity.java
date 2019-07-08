package com.mymusic.music.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.FriendAllData;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.FriendDetailActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.FriendAllAdapter;
import com.mymusic.music.base.BaseActivity;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class FriendListActivity extends BaseActivity {

    @BindView(R.id.Rc)
    RecyclerView rc;
    private String name;
    private FriendAllData data;


    @Override
    protected void initVariables(Intent intent) {
        name = intent.getStringExtra("name");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_friend_list);
    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        NetRequest.postFormRequest(UrlManager.FRIEND_ALL, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                data = GsonUtil.GsonToBean(result, FriendAllData.class);
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
    }

    private void initRc(List<FriendAllData.DataBean.ListBeanX> list) {
        rc.setLayoutManager(new LinearLayoutManager(this));
        FriendAllAdapter adapter = new FriendAllAdapter(R.layout.fragment_friend_all_item,list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(FriendListActivity.this, FriendDetailActivity.class);
                intent.putExtra("id",list.get(position).getCid());
                startActivity(intent);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.friend_all_bt:
                        TextView focus = view.findViewById(R.id.friend_all_bt);
                        focus.setBackgroundResource(R.drawable.isfocus);
                        focus.setText("取消关注");
                        initFocusFriend(true,position);
                        break;
                }
            }
        });
        adapter.notifyDataSetChanged();
        rc.setAdapter(adapter);
    }


    private void initFocusFriend(boolean isFocus, int i) {
        String url = "";
        if(isFocus){
            url = UrlManager.Focus_Friend;
        }else{
            url = UrlManager.NoFocus_Friend;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("touid",data.getData().getList().get(i).getCid());
        if(Live.getInstance().getToken(this) == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(url, map, Live.getInstance().getToken(this), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                Toast.makeText(FriendListActivity.this,"操作成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(FriendListActivity.this,"操作失败",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
    @OnClick({R.id.close})
    public void Click(View view){
        switch (view.getId()){
            case R.id.close:
                finish();
                break;
        }
    }
}
