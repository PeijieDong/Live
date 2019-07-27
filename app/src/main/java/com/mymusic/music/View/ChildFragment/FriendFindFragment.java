package com.mymusic.music.View.ChildFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.FriendFindData;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.Detail.FriendDetailActivity;
import com.mymusic.music.View.Activity.FriendListActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Activity.user.ListUserActivity;
import com.mymusic.music.View.Adapter.FriendFindRecyclerviewAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/6/1 16:39
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendFindFragment extends BaseFragment implements OnRefreshListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.refresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.friendFindRc)
    RecyclerView recyclerView;
    private FriendFindData data;
    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_friend_find_layout,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {


    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        initNet();
    }

    private void initNet() {
        NetRequest.postFormRequest(UrlManager.FRIEND_FIND, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                refreshLayout.finishRefresh();
                Log.e("33",result);
                FriendFindData data = GsonUtil.GsonToBean(result, FriendFindData.class);
                initRc(data);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                refreshLayout.finishRefresh();
                Log.d("33",e.getMessage());
            }
            @Override
            public void TokenFail() {
                refreshLayout.finishRefresh();
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }

    private void initRc(FriendFindData data) {
        this.data = data;
        FriendFindRecyclerviewAdapter adapter =
                new FriendFindRecyclerviewAdapter(R.layout.fragment_friend_find_item, data.getData().getList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.find_item_focus:
                        if(Live.getInstance().getUser(getContext()) == null){
                            Intent intent1 = new Intent(getContext(), LoginActivity.class);
                            getContext().startActivity(intent1);
                            return;
                        }
                        TextView focus = view.findViewById(R.id.find_item_focus);

                        if(focus.getText().toString().equals("取消关注")){
                            focus.setText("+关注");
                            focus.setBackgroundResource(R.drawable.focus);
                            initFocusFriend(false,position);
                        }else{
                            focus.setText("取消关注");
                            focus.setBackgroundResource(R.drawable.isfocus);
                            initFocusFriend(true,position);
                        }
                        break;
                    case R.id.four_head:
                        Intent intent = new Intent(getContext(), ListUserActivity.class);
                        intent.putExtra("FriendId",data.getData().getList().get(position).getCid());
                        getContext().startActivity(intent);
                        break;
                    case R.id.friend_list:
                        Intent intent1 = new Intent(getContext(), FriendListActivity.class);
                        intent1.putExtra("name",data.getData().getList().get(position).getName());
                        getContext().startActivity(intent1);
                        break;
                    case  R.id.image_Container1:
                        goActivity(data.getData().getList().get(position).getList().get(0).getId());
                        break;
                    case  R.id.image_Container2:
                        goActivity(data.getData().getList().get(position).getList().get(1).getId());
                        break;
                    case  R.id.image_Container3:
                        goActivity(data.getData().getList().get(position).getList().get(2).getId());
                        break;
                    case  R.id.image_Container4:
                        goActivity(data.getData().getList().get(position).getList().get(3).getId());
                        break;
                    case  R.id.image_Container5:
                        goActivity(data.getData().getList().get(position).getList().get(4).getId());
                        break;
                    case  R.id.image_Container6:
                        goActivity(data.getData().getList().get(position).getList().get(5).getId());
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
        refreshLayout.finishRefresh();

    }

    public void goActivity(String id){
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("id",id);
        Log.e("33",id);
        startActivity(intent);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getContext(), FriendDetailActivity.class);
        String cid = data.getData().getList().get(position).getCid();
        intent.putExtra("id",cid);
        startActivity(intent);
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
        if(Live.getInstance().getToken(getContext()) == null){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            return;
        }
        NetRequest.postFormHeadRequest(url, map, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                ToastUtil.show(getContext(),"操作成功",Toast.LENGTH_SHORT);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(getContext(),"操作失败",Toast.LENGTH_SHORT);
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
    }
}
