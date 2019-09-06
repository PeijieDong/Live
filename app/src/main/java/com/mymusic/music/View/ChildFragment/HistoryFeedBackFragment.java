package com.mymusic.music.View.ChildFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymusic.music.DataBean.FeedBack;
import com.mymusic.music.Live;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.LoginDialog;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Activity.Detail.HistoryFeedDetail;
import com.mymusic.music.View.Adapter.FeedBackRcAdapter;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class HistoryFeedBackFragment extends BaseFragment {

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.activity_history_feed_back,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }


    @BindView(R.id.Rc)
    RecyclerView Rc;


    @Override
    protected void LoadData() {
        initNet();
    }

    private void initNet() {
        loading();
        HashMap<String, String> map = new HashMap<>();
        map.put("page","1");
        NetRequest.postFormHeadRequest(UrlManager.History_FeedBack, map, Live.getInstance().getToken(getContext()), new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                FeedBack bean = GsonUtil.GsonToBean(result, FeedBack.class);
                Rc.setLayoutManager(new LinearLayoutManager(getContext()));
                FeedBackRcAdapter adapter = new FeedBackRcAdapter(R.layout.history_feedback_layout,bean.getData().getList());
                adapter.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.empty_layout,null));
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(getContext(), HistoryFeedDetail.class);
                        intent.putExtra("feedback",(Serializable) bean.getData().getList().get(position));
                        startActivity(intent);
                    }
                });
                Rc.setAdapter(adapter);
                hideloading();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                hideloading();
            }

            @Override
            public void TokenFail() {
                LoginDialog dialog = new LoginDialog(getContext());
                dialog.Show();
                hideloading();
            }
        });

    }

    @OnClick({R.id.back})
    public void ClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                getActivity().finish();
                break;
        }
    }
}
