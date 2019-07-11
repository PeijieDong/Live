package com.mymusic.music.View.Activity.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.FriendDetail;
import com.mymusic.music.DataBean.UserVideo;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.UserVideoAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/6/7 18:07
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class UserVideoFragment extends BaseFragment {

    @BindView(R.id.Rc)
    RecyclerView Rc;
    String id;
    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_user_video,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        id = bundle.getString("id");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid",id);
        map.put("page","1");
        NetRequest.postFormRequest(UrlManager.User_Video, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                FriendDetail bean = GsonUtil.GsonToBean(result, FriendDetail.class);
                Rc.setLayoutManager(new GridLayoutManager(getContext(),3));
                UserVideoAdapter adapter = new UserVideoAdapter(R.layout.user_video_item,bean.getData().getList());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.empty_layout, null);
                adapter.setEmptyView(view);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(getContext(), DetailsActivity.class);
//                        intent.putExtra("userVideo",(Serializable) bean.getData().getList());
                        intent.putExtra("id",bean.getData().getList().get(position).getId());
                        startActivity(intent);
                    }
                });
                Rc.setAdapter(adapter);
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
    protected void LoadData() {

    }
}
