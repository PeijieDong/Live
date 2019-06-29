package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/6/7 18:07
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class UserDynamicFragment extends BaseFragment {
    @BindView(R.id.Rc)
    RecyclerView Rc;
    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_user_dynamic,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","1");
        map.put("page","1");
        NetRequest.postFormRequest(UrlManager.User_Activity, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                HomeData bean = GsonUtil.GsonToBean(result, HomeData.class);
                Rc.setLayoutManager(new LinearLayoutManager(getContext()));
                HomePagerRecyclerViewAdapter adapter = new HomePagerRecyclerViewAdapter(bean.getData().getList());
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(getContext(), DetailsActivity.class);
                        intent.putExtra("id",bean.getData().getList().get(position).getId());
                        startActivity(intent);
                    }
                });
                Rc.setAdapter(adapter);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    @Override
    protected void LoadData() {

    }
}
