package com.mymusic.music.View.ChildFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.mymusic.music.DataBean.FriendAllData;
import com.mymusic.music.DataBean.FriendAllTitle;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.TopNavigation;
import com.mymusic.music.View.Adapter.FriendAllAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

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
public class FriendAllFragment extends BaseFragment implements TopNavigation.OnTabCheckListener {

    @BindView(R.id.topNavigation)
    TopNavigation navigation;
    @BindView(R.id.friend_all_rc)
    RecyclerView rc;
    private List<String> title;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_friend_all_layout,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initTitle();
    }

    private void initTab(List<String> list) {
        this.title = list;
        for (int i = 0;i<list.size();i++){
            navigation.addTab(new TopNavigation.Tab().setText(list.get(i)));
        }
        navigation.setCurrentItem(0);
        initNet(0);
        navigation.setOnTabChechListener(this);
    }

    @Override
    protected void LoadData() {

    }

    private void initTitle() {
        NetRequest.getFormRequest(UrlManager.FRIEND_ALL, null, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                FriendAllTitle title = GsonUtil.GsonToBean(result, FriendAllTitle.class);
                initTab(title.getData().getList());
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }


    @Override
    public void onTabSelected(View v, int position) {
        initNet(position);
    }

    private void initNet(int position) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",title.get(position));
        NetRequest.postFormRequest(UrlManager.FRIEND_ALL, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                FriendAllData data = GsonUtil.GsonToBean(result, FriendAllData.class);
                initRc(data.getData().getList());
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    private void initRc(List<FriendAllData.DataBean.ListBeanX> list) {
        rc.setLayoutManager(new LinearLayoutManager(getContext()));
        FriendAllAdapter adapter = new FriendAllAdapter(R.layout.fragment_friend_all_item,list);
        adapter.notifyDataSetChanged();
        rc.setAdapter(adapter);
    }
}
