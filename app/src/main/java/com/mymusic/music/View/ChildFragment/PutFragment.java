package com.mymusic.music.View.ChildFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.HomeData;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.Detail.FriendDetailActivity;
import com.mymusic.music.View.Activity.Detail.UserDetailActivity;
import com.mymusic.music.View.Activity.Detail.VideoPlayActivity;
import com.mymusic.music.View.Activity.Detail.VideoSingleActivity;
import com.mymusic.music.View.Activity.JubaoActivity;
import com.mymusic.music.View.Adapter.HomePagerRecyclerViewAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/7/4 22:12
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class PutFragment extends BaseFragment {

    @BindView(R.id.Rc)
    RecyclerView Rc;
    int id;
    private List<HomeData.DataBean.ListBean> list;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.put_fragment,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        id = bundle.getInt("position");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        initNet();
    }

    public void initNet(){
        checkLogin();
        HashMap<String, String> map = new HashMap<>();
        map.put("page","1");
        map.put("type",id+"");
        NetRequest.postFormHeadRequest(UrlManager.Publish, map, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                HomeData data = GsonUtil.GsonToBean(result, HomeData.class);
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

    TextView likeNum;
    ImageView likeIcon;
    private boolean isSelect = true;
    private void initRc(List<HomeData.DataBean.ListBean> list) {
        this.list = list;
        Rc.setLayoutManager(new LinearLayoutManager(getContext()));
        HomePagerRecyclerViewAdapter adapter = new HomePagerRecyclerViewAdapter(list);
        adapter.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.empty_layout,null));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(id == 1){
                    if(list.get(position).getType().equals("小视频")){
                        Intent intent = new Intent(getContext(), VideoSingleActivity.class);
                        intent.putExtra("playData",(Serializable) list.get(position));
                        intent.putExtra("position",position);
                        getContext().startActivity(intent);
                    }else {
                        Intent intent = new Intent(getContext(), DetailsActivity.class);
                        intent.putExtra("id", list.get(position).getId());
                        startActivity(intent);
                    }
                }
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.themeBt:
                        Intent intent = new Intent(getActivity(), FriendDetailActivity.class);
                        intent.putExtra("id",list.get(position).getCate());
                        getContext().startActivity(intent);
                        break;
                    case R.id.userBt:
                        Intent intentU = new Intent(getContext(), UserDetailActivity.class);
                        intentU.putExtra("UserId", list.get(position).getUid());
                        startActivity(intentU);
                        break;
                }

            }
        });
        Rc.setAdapter(adapter);
    }
}
