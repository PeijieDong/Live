package com.mymusic.music.View.ChildFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.FriendDetail;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.Detail.VideoPlayActivity;
import com.mymusic.music.View.Activity.post.PutVideoActivity;
import com.mymusic.music.View.Adapter.FriendDetailAdapter;
import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/6/9 21:50
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class FriendDetailFragment2 extends BaseFragment {

    @BindView(R.id.Rc)
    RecyclerView rc;
    private String id;
    private boolean isSelect = true;
    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.friend_detail_fragment2,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        id = bundle.getString("id");
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("type","2");
        NetRequest.postFormRequest(UrlManager.FRIEND_DETAILS_LIST, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                FriendDetail data = GsonUtil.GsonToBean(result, FriendDetail.class);
                rc.setLayoutManager(new GridLayoutManager(getContext(),3));
                FriendDetailAdapter adapter =
                        new FriendDetailAdapter(R.layout.friend_detail_fragment2_item,data.getData().getList());
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent1 = new Intent(getContext(), VideoPlayActivity.class);
                        intent1.putExtra("video",data);
                        intent1.putExtra("position",position);
                        startActivity(intent1);
                    }
                });
                rc.setAdapter(adapter);
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

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {

    }

}
