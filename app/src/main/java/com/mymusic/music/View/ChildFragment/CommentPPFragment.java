package com.mymusic.music.View.ChildFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.Art;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.Util.ToastUtil;
import com.mymusic.music.View.Activity.Detail.DetailsActivity;
import com.mymusic.music.View.Activity.Login.LoginActivity;
import com.mymusic.music.View.Adapter.ArRcAdpater;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/6/22 22:45
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class CommentPPFragment extends BaseFragment {

    @BindView(R.id.Rc)
    RecyclerView rc;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_pp_fragment,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("type","2");
        if(Live.getInstance().getToken(getContext()) == null){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            return;
        }
        loading();
        NetRequest.postFormHeadRequest(UrlManager.My_Comment, map, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("33",result);
                Art bean = GsonUtil.GsonToBean(result, Art.class);
                initView(bean);
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
        hideloading();
    }

    private void initView(Art bean) {
        rc.setLayoutManager(new LinearLayoutManager(getContext()));
        ArRcAdpater adpater = new ArRcAdpater(R.layout.art_rc_layout,bean.getData().getList());
        adpater.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.empty_layout,null));
        adpater.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("id",bean.getData().getList().get(position).getCid());
                startActivity(intent);
            }
        });
        adpater.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.close:
                        initClose(bean,position);
                        break;
                }
            }
        });
        rc.setAdapter(adpater);
    }

    private void initClose(Art bean, int position) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",bean.getData().getList().get(position).getVid());
        loading();
        NetRequest.postFormHeadRequest(UrlManager.Delete, map,Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                ToastUtil.show(getContext(),"删除成功",Toast.LENGTH_SHORT);
                initNet();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtil.show(getContext(),"删除失败",Toast.LENGTH_SHORT);
            }
            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getActivity());
                dialog.Show();
            }
        });
        hideloading();
    }
}
