package com.mymusic.music.View.ChildFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.music.DataBean.VideoItem;
import com.mymusic.music.R;
import com.mymusic.music.Util.GsonUtil;
import com.mymusic.music.Util.NetRequest;
import com.mymusic.music.View.Adapter.RcAdpaterVideo;
import com.mymusic.music.base.BaseFragment;
import com.mymusic.music.base.UrlManager;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Create By mr.mao in 2019/7/31 13:17
 * 我珍惜一眼而过的青春，才如此疯狂的对待未来
 **/
public class VideoItemFragment extends BaseFragment {

    @BindView(R.id.Rc)
    RecyclerView rc;
    String pid;

    @Override
    protected View CreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.item_video_fragment_layout,container,false);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        pid = bundle.getString("pid");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initNet();
    }

    private void initNet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",pid);
        NetRequest.postFormRequest(UrlManager.GET_PINDAO_DETAIL, map, new NetRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                VideoItem bean = GsonUtil.GsonToBean(result, VideoItem.class);
                rc.setLayoutManager(new LinearLayoutManager(getContext()));
                RcAdpaterVideo videoAdapter = new RcAdpaterVideo(R.layout.rc_adapter_item_video,bean.getData().getList());
                rc.setAdapter(videoAdapter);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void TokenFail() {

            }
        });
    }

    @Override
    protected void LoadData() {

    }
}
